package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * article status
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public final class ArticleStatus extends ValueObject {

    public static ArticleStatus of(int raw) {
        switch (raw) {
            case 0:
                return ArticleStatus.DRAFT;
            case 1:
                return ArticleStatus.PUBLISHED;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * draft
     */
    public static final ArticleStatus DRAFT = ArticleStatus.of(Status.DRAFT);
    /**
     * published
     */
    public static final ArticleStatus PUBLISHED = ArticleStatus.of(Status.PUBLISHED);

    private final Status status;

    @RequiredArgsConstructor
    public enum Status {

        /**
         * draft status
         */
        DRAFT(0),

        /**
         * published status
         */
        PUBLISHED(1);

        @Getter
        private final int value;

    }

}
