package com.whatakitty.jmore.framework.bootstrap;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

import com.whatakitty.jmore.framework.bootstrap.event.*;
import com.whatakitty.jmore.framework.bootstrap.listener.JMoreApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;

/**
 * JMore run listener
 *
 * @author WhatAKitty
 * @date 2019/04/21
 * @description
 **/
public class JMoreRunListener implements SpringApplicationRunListener, Ordered {

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
            new JMoreStartingEvent(this.application, this.args));
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        this.initialMulticaster.multicastEvent(new JMoreEnvironmentPreparedEvent(
            this.application, this.args, environment));
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        this.initialMulticaster.multicastEvent(new JMoreContextInitializedEvent(
            this.application, this.args, context));
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        for (JMoreApplicationListener<?> listener : this.application.getJmoreListeners()) {
            if (listener instanceof ApplicationContextAware) {
                ((ApplicationContextAware) listener).setApplicationContext(context);
            }
            context.addApplicationListener(listener);
        }

        // remove exists application event multicaster
        if (context.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)) {
            Object bean = context.getBeanFactory().getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME);
            context.getBeanFactory().destroyBean(bean);
        }
        // use the same event publisher
        context.getBeanFactory().registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, initialMulticaster);

        this.initialMulticaster.multicastEvent(
            new JMorePreparedEvent(this.application, this.args, context));
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        context.publishEvent(
            new JMoreStartedEvent(this.application, this.args, context));
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        context.publishEvent(new JMoreReadyEvent(application, args, context));
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        context.publishEvent(new JMoreFailedEvent(application, args, context, exception));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
