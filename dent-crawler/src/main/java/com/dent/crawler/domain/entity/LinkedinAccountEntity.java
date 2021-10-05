package com.dent.crawler.domain.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "LinkedinAccounts")
public class LinkedinAccountEntity extends BaseEntity {

    @Id
    private String email;
    private String password;
    private Date lastAccessDate;
    private Boolean status;

    public LinkedinAccountEntity() {
        super();
    }

    public LinkedinAccountEntity(String email, String password) {
        super();
        this.email = email;
        this.password = password;
        this.lastAccessDate = new Date();
        this.status = Boolean.TRUE;
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
