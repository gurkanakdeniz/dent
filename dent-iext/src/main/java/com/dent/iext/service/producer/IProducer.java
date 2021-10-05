package com.dent.iext.service.producer;

import com.dent.iext.service.producer.model.TopicName;

public interface IProducer {

    void sendMessage(TopicName name, Object message);

}
