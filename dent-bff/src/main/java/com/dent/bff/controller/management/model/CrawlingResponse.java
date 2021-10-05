package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class CrawlingResponse extends BaseResponse {

    private String id;

    public CrawlingResponse() {
        super();
    }

    public CrawlingResponse(String id) {
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
