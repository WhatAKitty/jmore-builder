package com.whatakitty.jmore.blog.application.security;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * security service
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Service
@RequiredArgsConstructor
public class SecurityService implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.findWithUsername(authentication.getName());
        boolean login = user.login(authentication.getCredentials().toString(), "");

        if (login) {
            // update user info such as last login ip and last login time
            userRepository.update(user);
            return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(0));
        } else {
            // login failed
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
