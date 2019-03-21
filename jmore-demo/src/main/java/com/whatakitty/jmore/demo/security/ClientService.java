package com.whatakitty.jmore.demo.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * client service
 *
 * @author WhatAKitty
 * @date 2019/02/26
 * @description
 **/
@Service("clientDetailsService")
public class ClientService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }

}
