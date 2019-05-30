package com.whatakitty.jmore.blog.domain.type;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * type aggregate root
 *
 * @author WhatAKitty
 * @date 2019/05/23
 * @description
 **/
@Getter
@Setter
@ToString(callSuper = true)
public final class Type extends AbstractAggregateRoot<String> {

    private String typeName;

    public Type(AggregateId<String> id) {
        super(id);
    }

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
