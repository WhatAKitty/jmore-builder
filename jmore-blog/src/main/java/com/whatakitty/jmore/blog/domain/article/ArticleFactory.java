package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.blog.domain.resource.Resource;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.type.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * article factory
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
public final class ArticleFactory {

    /**
     * create a new article
     *
     * @param author    the author
     * @param tags      the tags that marked the article
     * @param types     the types the article belongs
     * @param resources like pictures, videos linked with this article
     * @param content   the article's content
     * @param title     the article's title
     * @return article instance
     */
    public Article newArticle(User author,
                              List<String> tags,
                              List<Type> types,
                              List<Resource> resources,
                              String content,
                              String title) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(Author.of(author.getNickname()));
        article.setTags(tags.parallelStream().map(ArticleTag::of).collect(Collectors.toList()));
        article.setTypes(types);
        article.setResources(resources);
        return article;
    }

}
