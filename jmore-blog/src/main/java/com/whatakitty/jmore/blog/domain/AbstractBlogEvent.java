package com.whatakitty.jmore.blog.domain;

import com.whatakitty.jmore.framework.ddd.event.DomainEvent;

/**
 * abstract blog event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class AbstractBlogEvent extends DomainEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AbstractBlogEvent(Object source) {
        super(source);
    }

}
