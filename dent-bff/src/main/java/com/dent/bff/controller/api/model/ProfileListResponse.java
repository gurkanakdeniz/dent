package com.dent.bff.controller.api.model;

import java.util.List;

import com.dent.bff.common.model.BaseResponse;

public class ProfileListResponse extends BaseResponse {

    private List<UserData> users;

    public ProfileListResponse() {
        super();
    }

    public ProfileListResponse(List<UserData> users) {
        super();
        this.users = users;
    }

    public List<UserData> getUsers() {
        return users;
    }

    public void setUsers(List<UserData> users) {
        this.users = users;
    }

}
