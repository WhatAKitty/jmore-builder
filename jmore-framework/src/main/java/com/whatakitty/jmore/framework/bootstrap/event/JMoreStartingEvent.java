package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
public class JMoreStartingEvent extends JMoreApplicationEvent {

    /**
     * Create a new {@link JMoreStartingEvent} instance.
     *
     * @param application the current application
     * @param args        the arguments the application is running with
     */
    public JMoreStartingEvent(SpringApplication application, String[] args) {
        super(application, args);
    }

}
