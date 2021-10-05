package com.dent.iext.service.user.model;

import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.service.common.BaseRequest;

public class RequestUpdate extends BaseRequest {

    private static final long serialVersionUID = 5177784514513346977L;
    
    private UserModel userModel;

    public RequestUpdate(UserModel userModel) {
        super();
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
