package com.dent.bff.infrastructure.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CorsProperties {

    @Value("#{'${cors.allowedOrigins}'.split(',')}")
    private List<String> allowedOrigins;

    @Value("#{'${cors.allowedMethods}'.split(',')}")
    private List<String> allowedMethods;

    @Value("#{'${cors.allowedHeaders}'.split(',')}")
    private List<String> allowedHeaders;

    @Value("#{'${cors.exposedHeaders}'.split(',')}")
    private List<String> exposedHeaders;

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public List<String> getExposedHeaders() {
        return exposedHeaders;
    }

}
