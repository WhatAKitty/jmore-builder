package com.whatakitty.jmore.demo.infrastructure.dao.mysql;

import com.whatakitty.jmore.demo.DemoMapper;
import com.whatakitty.jmore.demo.domain.model.post.Post;
import org.apache.ibatis.annotations.Mapper;

/**
 * post mapper
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@Mapper
public interface PostMapper extends DemoMapper<Post> {
}
