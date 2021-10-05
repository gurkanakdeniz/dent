package com.dent.bff.controller.login.model;

import com.dent.bff.common.model.BaseResponse;

public class ResponseLogin extends BaseResponse {

    private Boolean status;
    private String username;
    private String role;

    public ResponseLogin() {
        super();
    }

    public ResponseLogin(Boolean status, String username, String role) {
        super();
        this.status = status;
        this.username = username;
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
