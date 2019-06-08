package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletags;

import lombok.Data;

@Data
public class ArticleTagsDO {
    private Long id;

    private Long articleId;

    private String tagName;
}