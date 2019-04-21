package com.whatakitty.jmore.framework.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;

/**
 * JMore run listener
 *
 * @author WhatAKitty
 * @date 2019/04/21
 * @description
 **/
public class JMoreRunListener implements SpringApplicationRunListener {

    private final JMoreApplication application;

    private final String[] args;

    private final ApplicationEventMulticaster initialMulticaster;

    public JMoreRunListener(SpringApplication application, String[] args) {
        Assert.isAssignable(JMoreApplication.class, application.getClass());
        this.application = (JMoreApplication) application;
        this.args = args;
        this.initialMulticaster = this.application.getApplicationEventMulticaster();
    }

    @Override
    public void starting() {
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

}
