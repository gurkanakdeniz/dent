package com.dent.crawler.service.linkedin.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestUpdate extends BaseRequest {

    private static final long serialVersionUID = 8142156375090218679L;
    
    private String email;
    private Boolean status;

    public RequestUpdate(String email, Boolean status) {
        super();
        this.email = email;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getStatus() {
        return status;
    }

}
