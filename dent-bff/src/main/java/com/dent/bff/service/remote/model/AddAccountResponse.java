package com.dent.bff.service.remote.model;

public class AddAccountResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String email;

    public AddAccountResponse() {
        super();
    }

    public AddAccountResponse(String email) {
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
