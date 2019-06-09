package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.resource;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceDO record);

    int insertSelective(ResourceDO record);

    ResourceDO selectByPrimaryKey(Long id);

    List<ResourceDO> selectByPrimaryKeys(@Param("ids") List<Long> ids);

    int updateByPrimaryKeySelective(ResourceDO record);

    int updateByPrimaryKeyWithBLOBs(ResourceDO record);

    int updateByPrimaryKey(ResourceDO record);
}