package com.dent.crawler.core.proxy.model;

import java.util.Date;

import com.dent.crawler.infrastructure.common.model.BaseModel;

public class ProxyAccountModel extends BaseModel {

    private static final long serialVersionUID = -6703040027300755681L;

    private String id;
    private String hostname;
    private String port;
    private String type;
    private String username;
    private String password;
    private Date lastAccessDate;
    private Boolean status;

    public ProxyAccountModel() {
        super();
    }

    public ProxyAccountModel(String id, String username, String password, String port, String type, String hostname,
            Date lastAccessDate, Boolean status) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.port = port;
        this.type = type;
        this.hostname = hostname;
        this.lastAccessDate = lastAccessDate;
        this.status = status;
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
