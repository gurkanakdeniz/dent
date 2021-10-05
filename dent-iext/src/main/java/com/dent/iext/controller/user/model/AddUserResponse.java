package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseResponse;

public class AddUserResponse extends BaseResponse {

    private static final long serialVersionUID = -280563822776908577L;
    
    private String username;

    public AddUserResponse(String username) {
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
