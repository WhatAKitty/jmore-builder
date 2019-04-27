package com.whatakitty.jmore.framework.bootstrap;

import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.*;
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
        // add listeners to multicaster
        if (initialMulticaster != null) {
            for (JMoreApplicationListener<?> listener : this.application.getJmoreListeners()) {
                initialMulticaster.addApplicationListener(listener);
            }
        }
    }

    @Override
    public void starting() {
        this.initialMulticaster.multicastEvent(
            new ApplicationStartingEvent(this.application, this.args));
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        this.initialMulticaster.multicastEvent(new ApplicationEnvironmentPreparedEvent(
            this.application, this.args, environment));
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        this.initialMulticaster.multicastEvent(new ApplicationContextInitializedEvent(
            this.application, this.args, context));
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        this.initialMulticaster.multicastEvent(
            new ApplicationPreparedEvent(this.application, this.args, context));
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        context.publishEvent(
            new ApplicationStartedEvent(this.application, this.args, context));
    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }

}
