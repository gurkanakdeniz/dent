package com.dent.bff.infrastructure.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class RequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    public static Map<String, String> headers(HttpServletRequest req) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Enumeration<String> headerNames = req.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String headerName = (String) headerNames.nextElement();
                    String headerValue = req.getHeader(headerName);
                    map.put(headerName, headerValue);
                }
            }
        } catch (Exception e) {
            logger.error("--- headers ---", e);
        }

        return map;
    }

    public static String extractRequestPayload(HttpServletRequest req) {
        String response = "";

        try {
            if (req instanceof ContentCachingRequestWrapper) {
                ContentCachingRequestWrapper request = (ContentCachingRequestWrapper) req;
                req.getParameterMap();
                response = new String(request.getContentAsByteArray());
            }
        } catch (Throwable e) {
            logger.error("--- extract request payload ---", e);
        }

        return response;
    }

    public static String extractRequestHeaders(HttpServletRequest req) {
        String response = "";

        try {
            response = JsonUtil.toJson(headers(req));
        } catch (Throwable e) {
            logger.error("--- extract request header ---", e);
        }

        return response;
    }

    public static String extractPath(HttpServletRequest request) {
        String response = "";

        try {
            StringBuilder path = new StringBuilder();
            path.append(request.getContextPath()).append(request.getServletPath());

            String info = request.getPathInfo();
            if (info != null) {
                path.append(info);
            }

            response = path.toString();
        } catch (Throwable e) {
            logger.error("--- extract path ---", e);
        }

        return response;
    }

}
