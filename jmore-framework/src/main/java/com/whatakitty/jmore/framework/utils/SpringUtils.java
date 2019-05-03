package com.whatakitty.jmore.framework.utils;

import com.whatakitty.jmore.framework.compilerule.annotations.NotNull;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring utils
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
@Component
public class SpringUtils implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

}
