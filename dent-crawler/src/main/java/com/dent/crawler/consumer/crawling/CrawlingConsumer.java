package com.dent.crawler.consumer.crawling;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.FilterType;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.domain.entity.CrawlingEntity;
import com.dent.crawler.domain.model.CrawlingModelDTO;
import com.dent.crawler.infrastructure.common.util.JsonUtil;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestAdd;

@Component
public class CrawlingConsumer {

    private final Logger logger = LoggerFactory.getLogger(CrawlingConsumer.class);

    @Autowired
    IJobService jobService;

    @KafkaListener(topics = "${mq.listen.topic.crawling}")
    public void consume(byte[] crawling) throws IOException {
        try {
            CrawlingEntity crawlingEntity = JsonUtil.toObject(crawling, CrawlingEntity.class);
            CrawlingModelDTO crawlingModel = crawlingEntity.getCrawlingModel();

            jobService.add(new RequestAdd(
                    new CrawlingModel(Mode.PROFILE, crawlingModel.getProfileId(), crawlingModel.getSummary(),
                            FilterType.fromCode(crawlingModel.getFilterType()), crawlingModel.getFilter())));
        } catch (Exception e) {
            logger.error("--- consume ---", e);
        }
    }
}
