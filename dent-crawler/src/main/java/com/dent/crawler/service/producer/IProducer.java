package com.dent.crawler.service.producer;

import com.dent.crawler.service.producer.model.RequestList;
import com.dent.crawler.service.producer.model.RequestSend;
import com.dent.crawler.service.producer.model.ResponseList;
import com.dent.crawler.service.producer.model.RequestDelete;
import com.dent.crawler.service.producer.model.ResponseDelete;

public interface IProducer {

    void send(RequestSend request);

    ResponseList list(RequestList request);
    
    ResponseDelete delete(RequestDelete request);

}
