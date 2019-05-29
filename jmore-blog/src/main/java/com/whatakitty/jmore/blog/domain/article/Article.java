package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.blog.domain.article.event.ArticleDroppedEvent;
import com.whatakitty.jmore.blog.domain.article.event.ArticlePublishedEvent;
import com.whatakitty.jmore.blog.domain.article.event.ArticleResourceChangedEvent;
import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * article aggreate root
 *
 * @author WhatAKitty
 * @date 2019/05/23
 * @description
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public final class Article extends AbstractAggregateRoot<String> {

    /**
     * the article title
     */
    private String title;
    /**
     * the article content
     */
    private String content;
    /**
     * the article status, {@link ArticleStatus};
     *
     * may be draft or published
     */
    private ArticleStatus articleStatus;
    /**
     * the article author
     */
    private User author;
    /**
     * the tags that the article related
     */
    private List<ArticleTag> tags;
    /**
     * the types that the article related
     */
    private List<Type> types;
    /**
     * resources used in the article
     */
    private List<Resource> resources;

    /**
     * create date of the article
     */
    private Date createDate;
    /**
     * modify date of the article
     */
    private Date modifyDate;
    /**
     * publish date of the article
     */
    private Date publishDate;

    /**
     * publish article and then publish an event {@link ArticlePublishedEvent}
     *
     * @return {true} published successfully
     */
    public boolean publish() {
        if (null != this.articleStatus && !ArticleStatus.DRAFT.equals(this.articleStatus)) {
            return false;
        }
        this.articleStatus = ArticleStatus.PUBLISHED;
        this.publishDate = new Date();
        publishEvent(new ArticlePublishedEvent(this));
        return true;
    }

    /**
     * save as draft
     *
     * @return
     */
    public boolean draft() {
        if (ArticleStatus.PUBLISHED.equals(this.articleStatus)) {
            return false;
        }
        this.articleStatus = ArticleStatus.DRAFT;
        return true;
    }

    /**
     * delete the article and then publish an event {@link ArticleDroppedEvent}
     *
     * @return
     */
    public boolean dropped() {
        invalid();
        publishEvent(new ArticleDroppedEvent(this));
        return true;
    }

    /**
     * modify the article's title
     *
     * @param title
     * @return
     */
    public boolean modifyTitle(String title) {
        this.title = title;
        return true;
    }

    /**
     * modify the article's content
     *
     * @param content
     * @param resources
     * @return
     */
    public boolean modifyContent(String content, List<Resource> resources) {
        // the same content
        if (this.content.equals(content)) {
            return true;
        }

        this.content = content;
        if (this.resources.equals(resources)) {
            final List<Resource> prevResources = new ArrayList<>(this.resources);
            final List<Resource> newResources = new ArrayList<>(resources);
            this.resources = resources;

            // publish event
            publishEvent(new ArticleResourceChangedEvent(this, prevResources, newResources));
        }


        return true;
    }

    /**
     * change the article's types
     *
     * @param types
     * @return
     */
    public boolean changeType(List<Type> types) {
        this.types = types;
        return true;
    }

    /**
     * change the article's tags
     *
     * @param newTags
     * @return
     */
    public boolean changeArticleTag(List<String> newTags) {
        this.tags = newTags.parallelStream().map(ArticleTag::of).collect(Collectors.toList());
        return true;
    }

}
