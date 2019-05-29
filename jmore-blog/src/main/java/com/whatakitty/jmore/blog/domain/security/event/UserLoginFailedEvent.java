package com.whatakitty.jmore.blog.domain.security.event;

import com.whatakitty.jmore.framework.ddd.event.DomainEvent;

/**
 * user login failed event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class UserLoginFailedEvent extends DomainEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserLoginFailedEvent(Object source) {
        super(source);
    }

}
