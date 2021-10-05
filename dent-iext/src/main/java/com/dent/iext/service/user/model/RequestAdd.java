package com.dent.iext.service.user.model;

import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.service.common.BaseRequest;

public class RequestAdd extends BaseRequest {

    private static final long serialVersionUID = -4229015358773705795L;
    
    private UserModel userModel;

    public RequestAdd(UserModel userModel) {
        super();
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

}
