package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * article test
 *
 * @author WhatAKitty
 * @date 2019/07/27
 * @description
 **/
@RunWith(SpringRunner.class)
public class ArticleTest {

    @Test
    public void test_publish_normal() {
        Article article = new Article(AggregateId.of(1L));
        boolean result = article.publish();
        Assert.assertTrue(result);
        Assert.assertEquals(ArticleStatus.PUBLISHED, article.getArticleStatus());
        Assert.assertNotNull(article.getPublishDate());
    }

    @Test
    public void test_publish_draft_status() {
        Article article = new Article(AggregateId.of(1L));
        article.setArticleStatus(ArticleStatus.DRAFT);
        boolean result = article.publish();
        Assert.assertTrue(result);
        Assert.assertEquals(ArticleStatus.PUBLISHED, article.getArticleStatus());
        Assert.assertNotNull(article.getPublishDate());
    }

    @Test
    public void test_publish_published_status() {
        Article article = new Article(AggregateId.of(1L));
        article.setArticleStatus(ArticleStatus.PUBLISHED);
        boolean result = article.publish();
        Assert.assertFalse(result);
    }

    @Test
    public void test_draft_normal() {
        Article article = new Article(AggregateId.of(1L));
        boolean result = article.draft();
        Assert.assertTrue(result);
        Assert.assertEquals(ArticleStatus.DRAFT, article.getArticleStatus());
        Assert.assertNull(article.getPublishDate());
    }

    @Test
    public void dropped() {
        Article article = new Article(AggregateId.of(1L));
    }

    @Test
    public void modifyTitle() {
    }

    @Test
    public void modifyContent() {
    }

    @Test
    public void changeType() {
    }

    @Test
    public void changeArticleTag() {
    }
}