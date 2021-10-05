package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class AccountUpdateResponse extends BaseResponse {

    private String email;

    public AccountUpdateResponse() {
        super();
    }

    public AccountUpdateResponse(String email) {
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
