package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class JobUpdateResponse extends BaseResponse {

    private String id;

    public JobUpdateResponse() {
        super();
    }

    public JobUpdateResponse(String id) {
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
