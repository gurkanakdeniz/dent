package com.dent.bff.service.remote.model;

public class DeleteUserResponse extends BaseResponse {

    private String username;

    public DeleteUserResponse() {
        super();
    }

    public DeleteUserResponse(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
