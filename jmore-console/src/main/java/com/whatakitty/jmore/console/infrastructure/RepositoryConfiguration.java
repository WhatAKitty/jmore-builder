package com.whatakitty.jmore.console.infrastructure;

import com.whatakitty.jmore.console.domain.command.CommandRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * repository configuration
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
@Configuration
public class RepositoryConfiguration {

    @ConditionalOnMissingBean(CommandRepository.class)
    @Bean
    public CommandRepository commandRepository() {
        return new DefaultCommandRepository();
    }

}
