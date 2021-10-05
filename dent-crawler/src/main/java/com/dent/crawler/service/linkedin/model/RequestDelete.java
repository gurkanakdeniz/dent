package com.dent.crawler.service.linkedin.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestDelete extends BaseRequest {

    private static final long serialVersionUID = 244659785489449121L;
    
    private String email;

    public RequestDelete(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
