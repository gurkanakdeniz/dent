package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseRequest;

public class AccountAddRequest extends BaseRequest {

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
