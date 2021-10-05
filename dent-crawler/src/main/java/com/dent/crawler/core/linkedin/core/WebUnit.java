package com.dent.crawler.core.linkedin.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import com.dent.crawler.core.proxy.core.ProxyClient;

public class WebUnit extends ChromeDriver implements WebDriver {

    private String username;
    private String password;
    private ProxyClient proxyClient;

    public WebUnit() {
        super();
    }

    public WebUnit(ChromeDriverService service, ChromeOptions options) {
        super(service, options);
    }

    public WebUnit(ChromeDriverService service) {
        super(service);
    }

    public WebUnit(ChromeOptions options) {
        super(options);
    }

    public static WebUnitBuilder getBuilder() {
        return new WebUnitBuilder().init();
    }

    public WebUnit failover() {
        return getBuilder().failover(this);
    }
    
    public WebUnit failover(boolean checkProxy) {
        return getBuilder().failover(this, checkProxy);
    }

    public WebUnit auth(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    public WebUnit proxy(ProxyClient proxyClient) {
        this.proxyClient = proxyClient;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ProxyClient getProxyClient() {
        return proxyClient;
    }

}
