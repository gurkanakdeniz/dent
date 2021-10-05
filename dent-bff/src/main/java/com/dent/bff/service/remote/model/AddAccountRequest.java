package com.dent.bff.service.remote.model;

public class AddAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public AddAccountRequest() {
        super();
    }

    public AddAccountRequest(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
