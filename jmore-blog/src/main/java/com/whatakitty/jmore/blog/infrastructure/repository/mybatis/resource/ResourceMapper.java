package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceDO record);

    int insertSelective(ResourceDO record);

    ResourceDO selectByPrimaryKey(Long id);

    List<ResourceDO> selectByPrimaryKeys(List<Long> ids);

    int updateByPrimaryKeySelective(ResourceDO record);

    int updateByPrimaryKeyWithBLOBs(ResourceDO record);

    int updateByPrimaryKey(ResourceDO record);
}