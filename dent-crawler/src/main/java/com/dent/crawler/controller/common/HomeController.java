package com.dent.crawler.controller.common;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dent.crawler.core.crawling.core.Crawling;
import com.dent.crawler.core.crawling.core.CrawlingProcessingModel;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.domain.entity.CrawlingEntity;
import com.dent.crawler.domain.repository.CrawlingRepository;
import com.dent.crawler.infrastructure.common.core.AppContext;
import com.dent.crawler.infrastructure.common.util.JsonUtil;
import com.dent.crawler.service.linkedin.ILinkedinService;
import com.dent.crawler.service.producer.IProducer;
import com.dent.crawler.service.producer.model.RequestSend;
import com.dent.crawler.service.producer.util.Mapper;
import com.dent.crawler.service.producer.util.TopicUtil;
import com.dent.crawler.service.proxy.IProxyService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Home")
public class HomeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    ILinkedinService linkedinService;

    @Autowired
    IProxyService proxyService;

    @Autowired
    CrawlingRepository crawlingRepository;

    @Autowired
    private TopicUtil topicUtil;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping
    public Object home() throws Exception {
        Object obj = "jedi";

//        CrawlingEntity entity = crawlingRepository.findById("5ff05ebeb3335f11c9ccede8").get();

//        CrawlingEntity entity = new CrawlingEntity("43", "jedi data", new Date(),
//                new CrawlingModelDTO("jedi mode", "jedi prfofile id"));
//
//        this.kafkaTemplate.send(topicUtil.getName(TopicName.IEXT), JsonUtil.toBytes(entity));

//        obj = Crawling.getBuilder().build(new CrawlingModel(Mode.PROFILE, "gürkanakdeniz")).processing();

        return obj;
    }

    @GetMapping("/p/{profileId}")
    public Object test(@PathVariable(name = "profileId", required = false) String profileId) throws Exception {
        Object obj = "jedi";

        if (profileId == null) {
            profileId = "gürkanakdeniz";
        }

        CrawlingModel crawlingModel = new CrawlingModel(Mode.PROFILE, profileId, "jedi", null, null);
        HashMap<String, CrawlingProcessingModel> map = Crawling.getBuilder().build(crawlingModel).processing();

        if (map != null && !map.isEmpty()) {
            for (String key : map.keySet()) {
                CrawlingEntity entity = new CrawlingEntity("42", map.get(key), new Date(),
                        Mapper.map(crawlingModel));
//                crawlingRepository.save(entity);
            }
        }
        
        obj = map;

        logger.info(JsonUtil.toJsonPretty(obj));

        return obj;
    }
    
    @GetMapping("/pp/{profileId}")
    public Object test2(@PathVariable(name = "profileId", required = false) String profileId) throws Exception {
        Object obj = "jedi";

        if (profileId == null) {
            profileId = "gürkanakdeniz";
        }

        CrawlingModel crawlingModel = new CrawlingModel(Mode.CORPORATE, profileId, "jedi", null, null);
        HashMap<String, CrawlingProcessingModel> map = Crawling.getBuilder().build(crawlingModel).processing();

        if (map != null && !map.isEmpty()) {
            for (String key : map.keySet()) {
                CrawlingEntity entity = new CrawlingEntity("42", map.get(key), new Date(),
                        Mapper.map(crawlingModel));
//                crawlingRepository.save(entity);
            }
        }
        
        obj = map;

        logger.info(JsonUtil.toJsonPretty(obj));

        return obj;
    }

}
