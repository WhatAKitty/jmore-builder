package com.whatakitty.jmore.blog.domain.article.event;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.framework.ddd.event.DomainEvent;
import java.util.List;

/**
 * article resource changed event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class ArticleResourceChangedEvent extends DomainEvent {

    private final List<Resource> prevResources;
    private final List<Resource> newResources;

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ArticleResourceChangedEvent(final Object source,
                                       final List<Resource> prevResources,
                                       final List<Resource> newResources) {
        super(source);
        this.prevResources = prevResources;
        this.newResources = newResources;
    }

}
