package com.dent.iext.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurer;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js, html}")
                .antMatchers("/i18n/**").antMatchers("/content/**").antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**").antMatchers("/h2/**").antMatchers("/actuator/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // INFO:GA: security config
        
        http.cors().and().exceptionHandling().and().csrf().disable().headers().frameOptions().disable().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/non-secured/**").permitAll()
//                .antMatchers("/resource/**").hasAnyAuthority("RESOURCE")
//                .antMatchers("/api/**").hasAnyAuthority("API")
                .anyRequest().permitAll().and().apply(securityConfigurer);

    }
}
