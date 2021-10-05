package com.dent.bff.service.remote.model;

public class InfoResponse extends BaseResponse {

    private UserModel user;

    public InfoResponse() {
        super();
    }

    public InfoResponse(UserModel user) {
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
