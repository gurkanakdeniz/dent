package com.dent.crawler.service.job.model;

import com.dent.crawler.service.common.model.BaseResponse;

public class ResponseAdd extends BaseResponse {

    private static final long serialVersionUID = -2458963009640075782L;
    
    private String id;

    public ResponseAdd(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
