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
    private Author author;
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
     * publish article
     *
     * @return {true} published successfully
     */
    public boolean publish() {
        this.articleStatus = ArticleStatus.PUBLISHED;
        this.publishDate = new Date();
        return true;
    }

    /**
     * save as draft
     *
     * @return
     */
    public boolean draft() {
        this.articleStatus = ArticleStatus.DRAFT;
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
