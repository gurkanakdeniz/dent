package com.dent.iext.core.iext.core;

import com.dent.iext.core.iext.model.UserModel;

public class InformationExtractionProcessingModel {

    private UserModel userModel;

    public InformationExtractionProcessingModel(UserModel userModel) {
        super();
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
