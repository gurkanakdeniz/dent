package com.dent.iext.service.producer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dent.iext.infrastructure.common.util.JsonUtil;
import com.dent.iext.service.producer.IProducer;
import com.dent.iext.service.producer.model.TopicName;
import com.dent.iext.service.producer.util.TopicUtil;

@Service
public class Producer implements IProducer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private TopicUtil topicUtil;

    @Override
    public void sendMessage(TopicName name, Object message) {
        try {
            this.kafkaTemplate.send(topicUtil.getName(name), JsonUtil.toBytes(message));
        } catch (Throwable e) {
            logger.error("--- send error ---", e);
        }
    }
}
