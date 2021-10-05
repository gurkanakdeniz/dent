package com.dent.iext.service.user.model;

import java.util.List;

import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.service.common.BaseResponse;

public class ResponseList extends BaseResponse {

    private static final long serialVersionUID = -8974334774161578446L;
    
    private List<UserModel> users;

    public ResponseList(List<UserModel> users) {
        super();
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return users;
    }

}
