package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleDO record);

    int insertSelective(ArticleDO record);

    ArticleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleDO record);

    int updateByPrimaryKeyWithBLOBs(ArticleDO record);

    int updateByPrimaryKey(ArticleDO record);
}