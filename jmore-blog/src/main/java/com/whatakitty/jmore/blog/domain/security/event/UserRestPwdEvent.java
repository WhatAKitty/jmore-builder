package com.whatakitty.jmore.blog.domain.security.event;

import com.whatakitty.jmore.framework.ddd.event.DomainEvent;

/**
 * user reset password event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class UserRestPwdEvent extends DomainEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRestPwdEvent(Object source) {
        super(source);
    }

}
