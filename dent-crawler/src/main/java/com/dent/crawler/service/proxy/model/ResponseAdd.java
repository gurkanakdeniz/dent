package com.dent.crawler.service.proxy.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseAdd extends BaseResponse {

    private static final long serialVersionUID = -2906317529680485393L;
    
    private String id;

    public ResponseAdd(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
