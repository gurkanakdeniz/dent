package com.dent.crawler.controller.job.model;

import com.dent.crawler.common.model.BaseResponse;

public class DeleteJobResponse extends BaseResponse {

    private static final long serialVersionUID = -6288369505618306362L;
    
    private String id;

    public DeleteJobResponse(String id) {
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
