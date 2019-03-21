package com.whatakitty.jmore.framework.validation;

import javax.validation.Validator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * validator config
 *
 * @author WhatAKitty
 * @date 2019/02/20
 * @description
 **/
@Configuration
public class ValidatorConfig {

    private final MessageSource messageSource;

    public ValidatorConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        return validator;
    }

}
