package com.dent.crawler.core.proxy.core;

public class ProxyClient {

    private String id;
    private String username;
    private String password;
    private String type;
    private int proxyPort;
    private String proxyHost;

    public ProxyClient(String id, String username, String password, String type, int proxyPort, String proxyHost) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.proxyPort = proxyPort;
        this.proxyHost = proxyHost;
    }

    public static ProxyClientBuilder getBuilder() {
        return new ProxyClientBuilder().init();
    }

    public String getId() {
        return id;
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

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

}
