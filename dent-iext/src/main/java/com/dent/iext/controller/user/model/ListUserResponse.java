package com.dent.iext.controller.user.model;

import java.util.List;

import com.dent.iext.common.model.BaseResponse;
import com.dent.iext.core.iext.model.UserModel;

public class ListUserResponse extends BaseResponse {

    private List<UserModel> users;

    public ListUserResponse(List<UserModel> users) {
        super();
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

}
