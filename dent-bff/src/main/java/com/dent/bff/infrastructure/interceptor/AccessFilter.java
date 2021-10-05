package com.dent.bff.infrastructure.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dent.bff.infrastructure.common.util.JwtUtil;

@Component
@Order(value = 10)
public class AccessFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        mdc(request);

        jwtUtil.auth(request);

        filterChain.doFilter(request, response);
    }

    private void mdc(HttpServletRequest request) {
        MDC.clear();
        if (request != null && request.getSession() != null) {
            MDC.put("sessionKey", request.getSession().getId());
        }
    }

}
