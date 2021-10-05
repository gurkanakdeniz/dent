package com.dent.crawler.service.linkedin.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestAdd extends BaseRequest {

    private static final long serialVersionUID = -3077318401274119886L;
    
    private String email;
    private String password;

    public RequestAdd(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
