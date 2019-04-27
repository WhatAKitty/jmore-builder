package com.whatakitty.jmore.framework.bootstrap.event;

import org.springframework.context.ApplicationEvent;

/**
 * jmore application based event
 *
 * @author WhatAKitty
 * @date 2019/04/28
 * @description
 **/
public abstract class JMoreApplicationEvent extends ApplicationEvent {

    /**
     * Create a new JMoreApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public JMoreApplicationEvent(Object source) {
        super(source);
    }

}
