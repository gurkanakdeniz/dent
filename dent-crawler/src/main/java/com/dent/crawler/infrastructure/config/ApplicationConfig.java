package com.dent.crawler.infrastructure.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {
    
    @Value("#{'${webdriver.args}'.split(';')}")
    private List<String> webDriverArgs;

    @Autowired
    private Environment env;

    public String getWebDriver() {
        return env.getProperty("webdriver.option", "webdriver.chrome.driver");
    }
    
    public String getWebDriverBinary() {
        return env.getProperty("webdriver.binary");
    }

    public String getWebDriverPath() {
        return env.getProperty("webdriver.path", "/usr/bin/chromedriver");
    }

    public List<String> getWebDriverArgs() {
        return this.webDriverArgs;
    }

}