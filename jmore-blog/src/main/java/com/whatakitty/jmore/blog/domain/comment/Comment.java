package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * comment aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Getter
@Setter
@ToString(callSuper = true)
public final class Comment extends AbstractAggregateRoot<String> {

    private String content;
    private User publisher;
    private CommentableResource commentableResource;
    private CommentPendingStatus pendingStatus;

    private Date commentTime;

    public Comment(AggregateId<String> id) {
        super(id);
    }

    /**
     * mark pended
     *
     * @return
     */
    public boolean markPended() {
        if (null != this.pendingStatus && !CommentPendingStatus.PENDING.equals(this.pendingStatus)) {
            return false;
        }
        this.pendingStatus = CommentPendingStatus.PENDED;
        return true;
    }

    /**
     * mark rejected
     *
     * @return
     */
    public boolean markReject() {
        if (null != this.pendingStatus && !CommentPendingStatus.PENDING.equals(this.pendingStatus)) {
            return false;
        }
        this.pendingStatus = CommentPendingStatus.REJECTED;
        return true;
    }

}
