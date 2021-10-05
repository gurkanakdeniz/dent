package com.dent.iext.service.producer.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dent.iext.service.producer.model.TopicName;

@Component
public class TopicUtil {

    @Value("#{${mq.topics}}")
    public Map<String, String> topics;

    public String getName(TopicName name) {
        if (topics != null) {
            return topics.get(name.getName());
        }

        return null;
    }

}
