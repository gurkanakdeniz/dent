package com.dent.crawler.domain.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProxyAccounts")
public class ProxyAccountEntity extends BaseEntity {

    @Id
    private String id;

    private String hostname;
    private String port;
    private String type;
    private String username;
    private String password;
    private Date lastAccessDate;
    private Boolean status;

    public ProxyAccountEntity() {
        super();
    }

    public ProxyAccountEntity(String username, String password, String port, String type,
            String hostname) {
        super();
        this.username = username;
        this.password = password;
        this.port = port;
        this.type = type;
        this.hostname = hostname;
        this.status = Boolean.TRUE;
        this.lastAccessDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
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
