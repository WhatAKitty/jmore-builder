package com.whatakitty.jmore.blog.application.listener;

import com.whatakitty.jmore.blog.application.config.ConfigDTO;
import com.whatakitty.jmore.blog.application.security.SecurityService;
import com.whatakitty.jmore.blog.application.security.UserDTO;
import com.whatakitty.jmore.blog.domain.config.Config;
import com.whatakitty.jmore.blog.domain.config.event.ConfigInitFinishedEvent;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import com.whatakitty.jmore.framework.compilerule.annotations.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * config init finish listener
 *
 * @author WhatAKitty
 * @date 2019/06/19
 * @description
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class ConfigInitListener implements JMoreApplicationListener<ConfigInitFinishedEvent> {

    private final SecurityService securityService;

    @Override
    public void onApplicationEvent(@NotNull ConfigInitFinishedEvent event) {
        final Config config = (Config) event.getSource();

        final UserDTO userDTO = new UserDTO();
        userDTO.setUsername(config.getAuthor().getUsername());
        userDTO.setPassword(config.getAuthor().getPassword());

        securityService.createAuthor(userDTO);
    }

}
