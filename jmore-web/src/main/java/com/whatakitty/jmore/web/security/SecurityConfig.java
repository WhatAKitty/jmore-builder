package com.whatakitty.jmore.web.security;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * security configuration
 *
 * @author WhatAKitty
 * @date 2019/02/23
 * @description
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.login:/login}")
    private String login;
    @Value("${spring.security.logout:/login?logout}")
    private String logoutSuccess;
    @Value("${spring.security.home:/home}")
    private String home;
    @Value("${spring.security.exclude-paths:}")
    private List<String> excluded;

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public SecurityConfig(UserDetailsService userDetailsService, @Lazy AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostConstruct
    public final void init() {
        excluded.add("/oauth/**");
        excluded.add(login);
        excluded.add("/actuator**");
        excluded.add("/error");
        excluded.add("/druid**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .headers().httpStrictTransportSecurity().disable()
            .and()
            .cors()
            .and()
            .authorizeRequests()
                .antMatchers(excluded.toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().loginPage(login).permitAll().defaultSuccessUrl(home)
            .and()
            .logout().logoutSuccessUrl(logoutSuccess);
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .parentAuthenticationManager(authenticationManager)
            .userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
