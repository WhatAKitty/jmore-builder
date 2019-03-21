package com.whatakitty.jmore.demo;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * demo mapper
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
public interface DemoMapper<T> extends Mapper<T>, IdsMapper<T>, InsertMapper<T> {
}
