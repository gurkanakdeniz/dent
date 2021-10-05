package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class JobAddResponse extends BaseResponse {

    private String id;

    public JobAddResponse() {
        super();
    }

    public JobAddResponse(String id) {
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
