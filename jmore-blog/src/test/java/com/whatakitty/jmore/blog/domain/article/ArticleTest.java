package com.whatakitty.jmore.blog.domain.article;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import com.whatakitty.jmore.framework.utils.SpringUtils;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
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

    @Before
    public void init() throws IllegalAccessException {
        final ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        final Field setApplicationContext = PowerMockito.field(SpringUtils.class, "applicationContext");
        setApplicationContext.set(SpringUtils.class, applicationContext);
    }

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