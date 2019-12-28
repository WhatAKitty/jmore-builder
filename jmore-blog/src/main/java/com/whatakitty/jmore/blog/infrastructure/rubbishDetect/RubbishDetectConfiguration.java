package com.whatakitty.jmore.blog.infrastructure.rubbishDetect;

import com.whatakitty.jmore.blog.domain.comment.RubbishDetectService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rubbish detect configuration
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
@Configuration
public class RubbishDetectConfiguration {

    @Bean
    @ConditionalOnMissingBean(RubbishDetectService.class)
    public RubbishDetectService rubbishDetectService() {
        return new SimpleRubbishDetectService();
    }

}
