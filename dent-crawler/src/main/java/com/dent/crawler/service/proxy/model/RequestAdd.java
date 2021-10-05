package com.dent.crawler.service.proxy.model;

import com.dent.crawler.service.common.model.BaseRequest;

public class RequestAdd extends BaseRequest {

    private static final long serialVersionUID = -9066085263831544002L;

    private String username;
    private String password;
    private String hostname;
    private String port;
    private String type;

    public RequestAdd(String username, String password, String port, String type, String hostname) {
        super();
        this.port = port;
        this.type = type;
        this.username = username;
        this.password = password;
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostname() {
        return hostname;
    }

}
