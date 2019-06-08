package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.infrastructure.repository.exception.IdGenerationException;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.id.IdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * id database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
@RequiredArgsConstructor
public class IdDatabaseRepository {

    private final IdMapper idMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public Long nextId() {
        if (idMapper.genNextId() != 1) {
            throw new IdGenerationException();
        }
        return idMapper.getNextId();
    }

}
