package com.dent.bff.service.remote.model;

public class AddProxyAccountRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    private String port;
    private String type;
    private String username;
    private String password;
    private String hostname;

    public AddProxyAccountRequest() {
        super();
    }

    public AddProxyAccountRequest(String port, String type, String username, String password, String hostname) {
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

}
