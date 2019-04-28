package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;

/**
 * jmore application based event
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
public abstract class JMoreApplicationEvent extends SpringApplicationEvent {

    public JMoreApplicationEvent(SpringApplication application, String[] args) {
        super(application, args);
    }

}
