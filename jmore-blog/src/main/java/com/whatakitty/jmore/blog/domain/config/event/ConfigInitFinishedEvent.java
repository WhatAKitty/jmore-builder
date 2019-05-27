package com.whatakitty.jmore.blog.domain.config.event;

import com.whatakitty.jmore.framework.ddd.event.DomainEvent;

/**
 * config init finished
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class ConfigInitFinishedEvent extends DomainEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ConfigInitFinishedEvent(Object source) {
        super(source);
    }
}
