package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TypeDO record);

    int insertSelective(TypeDO record);

    TypeDO selectByPrimaryKey(Long id);

    List<TypeDO> selectByPrimaryKeys(List<Long> ids);

    int updateByPrimaryKeySelective(TypeDO record);

    int updateByPrimaryKey(TypeDO record);


    // relation with article
    List<TypeDO> selectListByArticleId(Long articleId);


}