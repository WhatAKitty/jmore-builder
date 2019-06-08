package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletypes;

import java.util.List;

public interface ArticleTypesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleTypesDO record);

    int insertSelective(ArticleTypesDO record);

    int insertList(List<ArticleTypesDO> articleTypes);

    ArticleTypesDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleTypesDO record);

    int updateByPrimaryKey(ArticleTypesDO record);

    int deleteAllByArticleId(Long articleId);
}