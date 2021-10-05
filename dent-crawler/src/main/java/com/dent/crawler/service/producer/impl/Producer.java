package com.dent.crawler.service.producer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dent.crawler.core.crawling.model.CrawlingDataModel;
import com.dent.crawler.domain.entity.CrawlingEntity;
import com.dent.crawler.domain.repository.CrawlingRepository;
import com.dent.crawler.infrastructure.common.util.JsonUtil;
import com.dent.crawler.service.producer.IProducer;
import com.dent.crawler.service.producer.model.RequestDelete;
import com.dent.crawler.service.producer.model.RequestList;
import com.dent.crawler.service.producer.model.RequestSend;
import com.dent.crawler.service.producer.model.ResponseDelete;
import com.dent.crawler.service.producer.model.ResponseList;
import com.dent.crawler.service.producer.model.TopicName;
import com.dent.crawler.service.producer.util.Mapper;
import com.dent.crawler.service.producer.util.TopicUtil;

@Service
public class Producer implements IProducer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CrawlingRepository crawlingRepository;

    @Autowired
    private TopicUtil topicUtil;

    @Override
    public void send(RequestSend request) {
        CrawlingEntity entity = null;
        if (request.getCrawlingId() != null) {
            Optional<CrawlingEntity> crawlingEntity = crawlingRepository.findById(request.getCrawlingId());
            if (crawlingEntity != null && crawlingEntity.isPresent()) {
                entity = crawlingEntity.get();
            }
        } else {
            entity = new CrawlingEntity(request.getJobId(), request.getCrawlingData(), new Date(),
                    Mapper.map(request.getCrawlingModel()));
            crawlingRepository.save(entity);
        }

        if (entity != null) {
            try {
                this.kafkaTemplate.send(topicUtil.getName(TopicName.IEXT), JsonUtil.toBytes(entity));
            } catch (Throwable e) {
                logger.error("--- send error ---", e);
            }
        }
    }

    @Override
    public ResponseList list(RequestList request) {
        List<CrawlingDataModel> crawlings = new ArrayList<CrawlingDataModel>();

        if (request.getId() != null) {
            Optional<CrawlingEntity> entity = crawlingRepository.findById(request.getId());
            if (entity != null && entity.isPresent()) {
                CrawlingEntity crawling = entity.get();
                crawlings.add(new CrawlingDataModel(crawling.getId(), crawling.getCrawlingDate(), crawling.getJobId(),
                        crawling.getCrawlingData(), Mapper.map(crawling.getCrawlingModel())));
            }
        } else {
            List<CrawlingEntity> entities = crawlingRepository.findAll(Sort.by(Direction.DESC, "crawlingDate"));
            if (entities != null && entities.size() > 0) {
                for (CrawlingEntity item : entities) {
                    crawlings.add(new CrawlingDataModel(item.getId(), item.getCrawlingDate(), item.getJobId(),
                            item.getCrawlingData(), Mapper.map(item.getCrawlingModel())));
                }
            }
        }

        return new ResponseList(crawlings);
    }

    @Override
    public ResponseDelete delete(RequestDelete request) {
        Optional<CrawlingEntity> crawling = crawlingRepository.findById(request.getId());

        try {
            CrawlingEntity entity = crawling.get();
            this.kafkaTemplate.send(topicUtil.getName(TopicName.IEXT_DELETE), JsonUtil.toBytes(entity));
        } catch (Throwable e) {
            logger.error("--- send error ---", e);
        }
        
        crawlingRepository.deleteById(request.getId());
        return new ResponseDelete(request.getId());
    }

}
