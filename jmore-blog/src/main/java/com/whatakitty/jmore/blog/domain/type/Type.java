package com.whatakitty.jmore.blog.domain.type;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * type aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/23
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public final class Type extends AbstractAggregateRoot<Long> {

    private String typeName;

    /**
     * destory type
     *
     * @param relatedArticles
     * @return
     */
    public boolean destroy(List<Article> relatedArticles) {
        // there area no article belongs to the type
        if (!relatedArticles.isEmpty()) {
            return false;
        }

        // set the type invalid
        invalid();
        return true;
    }

}
