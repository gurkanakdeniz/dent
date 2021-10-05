package com.dent.bff.controller.login.model;

import com.dent.bff.common.model.BaseRequest;

public class RequestLogin extends BaseRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
