package com.dent.crawler.service.producer.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestDelete extends BaseRequest {

    private String id;

    public RequestDelete(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
