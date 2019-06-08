package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articleresource;

import java.util.List;

public interface ArticleResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleResourceDO record);

    int insertSelective(ArticleResourceDO record);

    int insertList(List<ArticleResourceDO> articleResources);

    ArticleResourceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleResourceDO record);

    int updateByPrimaryKey(ArticleResourceDO record);

    int deleteAllByArticleId(Long articleId);
}