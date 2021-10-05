package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseResponse;

public class UpdateUserResponse extends BaseResponse {

    private static final long serialVersionUID = -8938737434753808177L;
    
    private String username;

    public UpdateUserResponse(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
