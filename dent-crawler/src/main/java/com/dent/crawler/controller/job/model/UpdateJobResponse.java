package com.dent.crawler.controller.job.model;

import com.dent.crawler.common.model.BaseResponse;

public class UpdateJobResponse extends BaseResponse {
    private static final long serialVersionUID = 5541690020661573056L;

    private String id;

    public UpdateJobResponse(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
