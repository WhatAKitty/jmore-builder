package com.whatakitty.jmore.demo.infrastructure.dao.mysql;

import com.whatakitty.jmore.demo.DemoMapper;
import com.whatakitty.jmore.demo.infrastructure.dao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Mapper
public interface UserMapper extends DemoMapper<UserDO> {
}
