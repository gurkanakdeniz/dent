package com.dent.crawler.service.proxy.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseUpdate extends BaseResponse {

    private static final long serialVersionUID = -3566713696332834332L;

    private String id;

    public ResponseUpdate(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
