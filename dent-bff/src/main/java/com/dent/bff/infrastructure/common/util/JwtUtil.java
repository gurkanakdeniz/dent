package com.dent.bff.infrastructure.common.util;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dent.bff.infrastructure.common.model.User;
import com.dent.bff.infrastructure.config.ApplicationConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private String AUTHORIZATION = "Authorization";
    private String BEARER = "Bearer ";

    private String COOKIE_NAME = "Dent-Auth";

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Autowired
    private ApplicationConfig applicationConfig;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(applicationConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void authentication(User user, String token) {
        SecurityContextHolder.clearContext();
        if (user != null && user.getAuthRole() != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, token,
                    AuthorityUtils.createAuthorityList(user.getAuthRole().getCode()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    public User auth(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        User user = null;
        String token = null;

        try {
            token = request.getHeader(AUTHORIZATION);

            if (token == null) {
                token = request.getHeader(AUTHORIZATION.toLowerCase());

                if (token == null) {
                    token = request.getHeader(COOKIE_NAME);

                    if (token == null) {
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie item : cookies) {
                                if (COOKIE_NAME.equalsIgnoreCase(item.getName())) {
                                    token = item.getValue();
                                }
                            }
                        }
                    }
                }
            }

            if (token != null) {
                token = token.replace(BEARER, "");

                Claims body = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                        .getBody();
                user = JsonUtil.toObject(body.getSubject(), User.class);

                // INFO:GA: ideal de client session tablosundan kontrol edilmeli
            }
        } catch (Exception e) {
            logger.error("--- auth ---", e);
        }

        authentication(user, token);
        return user;
    }

    public String auth(User user, HttpServletResponse response) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Long tokenExpiration = applicationConfig.getTokenExpiration();
        long expMillis = nowMillis + tokenExpiration.longValue();
        Date exp = new Date(expMillis);

        // INFO:GA: ideal de client session tablosuna kayit edilmeli
        String token = Jwts.builder().setId(UUID.randomUUID().toString()).setIssuedAt(now).setExpiration(exp)
                .setSubject(JsonUtil.toJson(user)).signWith(getSigningKey()).compact();

        add(token, Long.valueOf(tokenExpiration.longValue() / 1000).intValue(), response);
        return token;
    }

    private void add(String token, int expiry, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
        response.addHeader(AUTHORIZATION, BEARER + token);
        response.addHeader(COOKIE_NAME, token);
    }

}
