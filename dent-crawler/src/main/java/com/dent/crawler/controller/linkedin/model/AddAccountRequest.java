package com.dent.crawler.controller.linkedin.model;

import com.dent.crawler.common.model.BaseRequest;

public class AddAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 7153901521710544051L;
    
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
