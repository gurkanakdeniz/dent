package com.dent.bff.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfig {

    @Autowired
    private Environment env;

    public String getSecret() {
        return env.getProperty("jwt.secret.key", "yodadoornotdothereisnotryyodadoornotdothereisnotryyoda");
    }

    public Long getTokenExpiration() {
        String exp = env.getProperty("jwt.expiration");

        if (exp == null) {
            return Long.valueOf(4200000);
        }

        return Long.valueOf(exp);
    }

}