package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.articletags;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleTagsDO record);

    int insertSelective(ArticleTagsDO record);

    int insertList(@Param("articleTagsDOS") List<ArticleTagsDO> articleTagsDOS);

    ArticleTagsDO selectByPrimaryKey(Long id);

    List<ArticleTagsDO> selectListByArticleId(Long articleId);

    int updateByPrimaryKeySelective(ArticleTagsDO record);

    int updateByPrimaryKey(ArticleTagsDO record);

    int deleteAllTagsByArticleId(@Param("articleId") Long articleId);
}