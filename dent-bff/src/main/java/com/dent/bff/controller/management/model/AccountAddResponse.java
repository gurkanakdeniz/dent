package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class AccountAddResponse extends BaseResponse {

    private String email;

    public AccountAddResponse() {
        super();
    }

    public AccountAddResponse(String email) {
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
