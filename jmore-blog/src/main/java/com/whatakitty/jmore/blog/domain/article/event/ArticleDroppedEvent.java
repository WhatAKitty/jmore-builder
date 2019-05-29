package com.whatakitty.jmore.blog.domain.article.event;

import com.whatakitty.jmore.framework.ddd.event.DomainEvent;

/**
 * article dropped event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class ArticleDroppedEvent extends DomainEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ArticleDroppedEvent(Object source) {
        super(source);
    }

}
