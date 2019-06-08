package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletypes;

import lombok.Data;

@Data
public class ArticleTypesDO {
    private Long id;

    private Long articleId;

    private Long typeId;
}