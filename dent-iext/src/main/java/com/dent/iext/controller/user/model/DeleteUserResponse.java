package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseResponse;

public class DeleteUserResponse extends BaseResponse {

    private static final long serialVersionUID = -4504653505658968803L;
    
    private String username;

    public DeleteUserResponse(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
