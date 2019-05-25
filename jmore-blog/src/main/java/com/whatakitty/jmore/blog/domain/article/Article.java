package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.type.Type;
import com.whatakitty.jmore.framework.ddd.domain.AbstractAggregateRoot;
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
public final class Article extends AbstractAggregateRoot<Long> {

    private String title;
    private String content;
    private ArticleStatus status;
    private Author author;
    private List<ArticleTag> tags;
    private List<Type> types;
    private List<Resource> resources;

    private Date createDate;
    private Date modifyDate;
    private Date publishDate;

    /**
     * publish article
     *
     * @return
     */
    public boolean publish() {
        this.status = ArticleStatus.PUBLISHED;
        this.publishDate = new Date();
        return true;
    }

    /**
     * save as draft
     *
     * @return
     */
    public boolean draft() {
        this.status = ArticleStatus.DRAFT;
        return true;
    }

    /**
     * delete the article
     *
     * @return
     */
    public boolean dropped() {
        invalid();
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
        this.content = content;
        this.resources = resources;
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
