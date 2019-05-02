package com.whatakitty.jmore.console;

import com.whatakitty.jmore.console.domain.context.ConsoleContextFactory;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * JMore console application
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
@RequiredArgsConstructor
@Component
public final class JMoreConsole implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final ConsoleContextFactory consoleContextFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collection<JMoreConsoleRunner> runners = applicationContext.getBeansOfType(JMoreConsoleRunner.class).values();
        if (runners.size() > 1) {
            throw new IllegalStateException("There should be only one jmore runner");
        }

        for (JMoreConsoleRunner runner : runners) {
            runner.run(consoleContextFactory.create(this, applicationContext, args));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "ApplicationContext should not be null");
        this.applicationContext = applicationContext;
    }
}
