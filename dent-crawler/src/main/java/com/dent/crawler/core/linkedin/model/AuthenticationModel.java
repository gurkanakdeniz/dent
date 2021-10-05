package com.dent.crawler.core.linkedin.model;

import java.util.Date;

import com.dent.crawler.infrastructure.common.model.BaseModel;

public class AuthenticationModel extends BaseModel {

    private static final long serialVersionUID = -7448114319561067519L;

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
