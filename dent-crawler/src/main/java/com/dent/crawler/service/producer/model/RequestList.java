package com.dent.crawler.service.producer.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestList extends BaseRequest {

    private String id;

    public RequestList() {
        super();
    }

    public RequestList(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
