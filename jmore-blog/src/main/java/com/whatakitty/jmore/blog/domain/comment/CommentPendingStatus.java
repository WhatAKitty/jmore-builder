package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * comment pending status
 *
 * @author WhatAKitty
 * @date 2019/05/24
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class CommentPendingStatus extends ValueObject {

    /**
     * pending
     */
    public static CommentPendingStatus PENDING = CommentPendingStatus.of(Status.PENDING);
    /**
     * pended
     */
    public static CommentPendingStatus PENDED = CommentPendingStatus.of(Status.PENDED);
    /**
     * rejected
     */
    public static CommentPendingStatus REJECTED = CommentPendingStatus.of(Status.REJECTED);

    private final Status status;

    /**
     * comment pending status inner class
     */
    @RequiredArgsConstructor
    private enum Status {

        /**
         * pending
         */
        PENDING(0),

        /**
         * pended
         */
        PENDED(1),

        /**
         * rejected
         */
        REJECTED(2);

        @Getter
        private final int value;

    }

}
