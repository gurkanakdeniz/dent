package com.dent.crawler.controller.linkedin.model;

import com.dent.crawler.common.model.BaseRequest;

public class UpdateAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 4165388528589814819L;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
