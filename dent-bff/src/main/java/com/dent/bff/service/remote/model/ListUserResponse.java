package com.dent.bff.service.remote.model;

import java.util.List;

public class ListUserResponse extends BaseResponse {

    private List<UserModel> users;

    public ListUserResponse() {
        super();
    }

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
