package com.dent.bff.service.remote.model;

import java.util.Date;

public class AuthenticationModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private Date lastAccessDate;
    private Boolean status;

    public AuthenticationModel() {
        super();
    }

    public AuthenticationModel(String email, String password, Date lastAccessDate, Boolean status) {
        super();
        this.email = email;
        this.password = password;
        this.lastAccessDate = lastAccessDate;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
