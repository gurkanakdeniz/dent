package com.dent.crawler.controller.linkedin.model;

import com.dent.crawler.common.model.BaseResponse;

public class AddAccountResponse extends BaseResponse {

    private static final long serialVersionUID = -818360339542318679L;
    
    private String email;

    public AddAccountResponse() {
        super();
    }

    public AddAccountResponse(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
