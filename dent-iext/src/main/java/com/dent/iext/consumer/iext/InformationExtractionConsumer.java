package com.dent.iext.consumer.iext;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dent.iext.core.iext.core.InformationExtraction;
import com.dent.iext.core.iext.core.InformationExtractionProcessingModel;
import com.dent.iext.core.iext.model.ConsumerCrawlingModel;
import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.infrastructure.common.util.JsonUtil;
import com.dent.iext.service.user.IUserService;
import com.dent.iext.service.user.model.RequestAdd;
import com.dent.iext.service.user.model.RequestInfoDelete;

@Component
public class InformationExtractionConsumer {

    private final Logger logger = LoggerFactory.getLogger(InformationExtractionConsumer.class);

    @Autowired
    private IUserService userService;

    @KafkaListener(topics = "${mq.listen.topic.iext}")
    public void consume(byte[] crawling) throws IOException {
        try {
            ConsumerCrawlingModel crawlingModel = JsonUtil.toObject(crawling, ConsumerCrawlingModel.class);
            userService.infoDelete(new RequestInfoDelete(crawlingModel.getCrawlingModel().getProfileId(),
                    crawlingModel.getId(), true));

            InformationExtractionProcessingModel processing = InformationExtraction.getBuilder().build(crawlingModel)
                    .processing();
            UserModel userModel = processing.getUserModel();
            userService.add(new RequestAdd(userModel));
        } catch (Exception e) {
            logger.error("--- consume ---", e);
        }
    }

    @KafkaListener(topics = "${mq.listen.topic.iextdelete}")
    public void consumeDelete(byte[] crawling) throws IOException {
        try {
            ConsumerCrawlingModel crawlingModel = JsonUtil.toObject(crawling, ConsumerCrawlingModel.class);
            userService.infoDelete(
                    new RequestInfoDelete(crawlingModel.getCrawlingModel().getProfileId(), crawlingModel.getId()));
        } catch (Exception e) {
            logger.error("--- consume delete ---", e);
        }
    }
}
