package com.whatakitty.jmore.web.security;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
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
@ConditionalOnProperty(name = "jmore.security.type", havingValue = "normal")
@ConditionalOnMissingBean(WebSecurityConfigurerAdapter.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final List<String> COMMON_EXCLUDE_PATHES;

    @Value("${jmore.security.login:/login}")
    private String login;
    @Value("${jmore.security.logout:/login?logout}")
    private String logoutSuccess;
    @Value("${jmore.security.home:/home}")
    private String home;
    @Value("${jmore.security.exclude-paths:}")
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
        excluded.addAll(COMMON_EXCLUDE_PATHES);
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

    @Configuration
    @EnableWebSecurity
    @ConditionalOnProperty(name = "jmore.security", matchIfMissing = true)
    @ConditionalOnMissingBean(WebSecurityConfigurerAdapter.class)
    public static class DisabledSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private final AuthenticationManager authenticationManager;

        protected DisabledSecurityConfiguration(@Lazy AuthenticationManager authenticationManager) {
            super(true);
            this.authenticationManager = authenticationManager;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.authorizeRequests().anyRequest().permitAll();
            // @formatter:on
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                .antMatchers("/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .parentAuthenticationManager(authenticationManager);
        }

    }

    @Configuration
    @EnableWebSecurity
    @ConditionalOnProperty(name = "jmore.security.type", havingValue = "custom")
    @ConditionalOnMissingBean(WebSecurityConfigurerAdapter.class)
    public static class CustomizedAuthenticationConfiguration extends WebSecurityConfigurerAdapter {

        private final AuthenticationProvider authenticationProvider;

        @Value("${jmore.security.exclude-paths:}")
        private List<String> excluded;

        protected CustomizedAuthenticationConfiguration(AuthenticationProvider authenticationProvider) {
            super(true);
            this.authenticationProvider = authenticationProvider;
        }

        @PostConstruct
        public void init() {
            excluded.addAll(COMMON_EXCLUDE_PATHES);
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
                    .anyRequest().authenticated();
            // @formatter:on
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .authenticationProvider(authenticationProvider);
        }

    }

    static {
        COMMON_EXCLUDE_PATHES = new ArrayList<>(16);
        COMMON_EXCLUDE_PATHES.add("/actuator**");
        COMMON_EXCLUDE_PATHES.add("/error");
        COMMON_EXCLUDE_PATHES.add("/druid**");
    }

}
