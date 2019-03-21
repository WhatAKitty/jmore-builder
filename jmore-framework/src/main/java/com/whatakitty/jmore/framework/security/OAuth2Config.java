package com.whatakitty.jmore.framework.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * security configuration with oauth2
 *
 * @author WhatAKitty
 * @date 2019/02/23
 * @description
 **/
@Configuration
public class OAuth2Config {

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Value("${spring.security.oauth2.resource-id:oauth2-server}")
        private String resourceId;
        @Value("${spring.security.oauth2.resource-path:/api/**}")
        private String protectedResource;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(resourceId).stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .requestMatchers()
                    .antMatchers(protectedResource)
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated();
            // @formatter:on
        }

    }


    @Configuration
    @EnableAuthorizationServer
    @EnableGlobalMethodSecurity(securedEnabled = true)
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        private final TokenStore tokenStore;
        private final ClientDetailsService clientDetailsService;
        private final UserDetailsService userDetailsService;
        private final AuthenticationManager authenticationManager;

        @Value("${spring.security.oauth2.resource-id:oauth2-server}")
        private String resourceId;

        public AuthorizationServerConfiguration(TokenStore tokenStore,
                                                ClientDetailsService clientDetailsService,
                                                UserDetailsService userDetailsService,
                                                @Lazy AuthenticationManager authenticationManager) {
            this.tokenStore = tokenStore;
            this.userDetailsService = userDetailsService;
            this.authenticationManager = authenticationManager;
            this.clientDetailsService = clientDetailsService;
        }

        @Bean
        @ConditionalOnMissingBean(PasswordEncoder.class)
        public PasswordEncoder passwordEncoder() {
            // default plain text password encoder
            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return charSequence.equals(s);
                }
            };
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.allowFormAuthenticationForClients();
            oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
        }

    }
}
