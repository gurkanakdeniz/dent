package com.dent.bff.controller.management.model;

import com.dent.bff.common.model.BaseResponse;

public class UserDeleteResponse extends BaseResponse {

    private String username;

    public UserDeleteResponse() {
        super();
    }

    public UserDeleteResponse(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
