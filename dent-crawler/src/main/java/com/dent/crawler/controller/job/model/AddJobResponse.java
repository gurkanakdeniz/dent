package com.dent.crawler.controller.job.model;

import com.dent.crawler.common.model.BaseResponse;

public class AddJobResponse extends BaseResponse {
    
    private static final long serialVersionUID = -8390916314399424685L;
    
    private String id;

    public AddJobResponse(String id) {
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
