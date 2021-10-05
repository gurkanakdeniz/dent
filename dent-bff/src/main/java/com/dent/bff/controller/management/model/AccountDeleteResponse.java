package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class AccountDeleteResponse extends BaseResponse {
    private String email;

    public AccountDeleteResponse() {
        super();
    }

    public AccountDeleteResponse(String email) {
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