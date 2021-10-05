package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseResponse;
import com.dent.iext.core.iext.model.UserModel;

public class InfoResponse extends BaseResponse {

    private UserModel user;

    public InfoResponse() {
        super();
    }

    public InfoResponse(UserModel user) {
        super();
        this.user = user;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
