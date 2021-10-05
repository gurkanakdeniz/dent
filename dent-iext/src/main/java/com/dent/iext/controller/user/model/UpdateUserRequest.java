package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseRequest;
import com.dent.iext.core.iext.model.UserModel;

public class UpdateUserRequest extends BaseRequest {

    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
