package com.dent.iext.controller.user.model;

import com.dent.iext.common.model.BaseRequest;
import com.dent.iext.core.iext.model.UserModel;

public class AddUserRequest extends BaseRequest {

    private static final long serialVersionUID = 8852184514734360014L;
    
    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
