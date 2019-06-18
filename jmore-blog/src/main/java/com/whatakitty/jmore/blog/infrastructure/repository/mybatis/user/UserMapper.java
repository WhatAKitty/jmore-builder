package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.user;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectFirst();

    UserDO selectByPrimaryKey(Long id);

    UserDO selectDetailedByPrimaryKey(Long id);

    UserDO selectAllByPrimaryKey(Long id);

    UserDO selectByUsername(@Param("username") String username);

    UserDO selectAllByUsername(@Param("username") String username);

    int updateByPrimaryKeySelective(UserDO record);

    int updateDetailsByPrimaryKeySelective(UserDO record);

    int updateAccessByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}