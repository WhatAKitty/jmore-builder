package com.whatakitty.jmore.blog.domain.article.event;

import com.whatakitty.jmore.blog.domain.AbstractBlogEvent;

/**
 * article published event
 *
 * @author WhatAKitty
 * @date 2019/05/27
 * @description
 **/
public class ArticlePublishedEvent extends AbstractBlogEvent {

    /**
     * Create a new Domain Event.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ArticlePublishedEvent(Object source) {
        super(source);
    }

}
