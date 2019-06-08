package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.comment.Comment;
import com.whatakitty.jmore.blog.domain.comment.CommentRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * comment database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
public class CommentDatabaseRepository implements CommentRepository {
    @Override
    public AggregateId<Long> nextId() {
        return null;
    }

    @Override
    public void add(Comment comment) {

    }

    @Override
    public void remove(Comment comment) {

    }
}
