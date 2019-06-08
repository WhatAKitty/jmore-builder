package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.id;

/**
 * id mapper
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
public interface IdMapper {

    int genNextId();

    Long getNextId();

}
