package com.dent.crawler.service.producer.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseDelete extends BaseResponse {

    private String id;

    public ResponseDelete() {
        super();
    }

    public ResponseDelete(String id) {
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
