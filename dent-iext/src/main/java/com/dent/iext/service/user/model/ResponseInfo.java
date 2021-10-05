package com.dent.iext.service.user.model;

import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.service.common.BaseResponse;

public class ResponseInfo extends BaseResponse {

    private UserModel user;

    public ResponseInfo() {
        super();
    }

    public ResponseInfo(UserModel user) {
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
