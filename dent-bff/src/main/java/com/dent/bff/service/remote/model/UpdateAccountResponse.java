package com.dent.bff.service.remote.model;

public class UpdateAccountResponse extends BaseResponse {
    private static final long serialVersionUID = 1L;

    private String email;

    public UpdateAccountResponse() {
        super();
    }

    public UpdateAccountResponse(String email) {
        super();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
