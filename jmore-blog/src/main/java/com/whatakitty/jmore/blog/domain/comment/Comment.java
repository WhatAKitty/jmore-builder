package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * comment aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public final class Comment extends AbstractAggregateRoot<Long> {

    private String content;
    private User publisher;
    private Article article;
    private CommentPendingStatus pendingStatus;

    /**
     * mark pended
     *
     * @return
     */
    public boolean markPended() {
        this.pendingStatus = CommentPendingStatus.PENDED;
        return true;
    }

    /**
     * mark rejected
     *
     * @return
     */
    public boolean markReject() {
        this.pendingStatus = CommentPendingStatus.REJECTED;
        return true;
    }

}
