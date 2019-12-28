package com.whatakitty.jmore.blog.infrastructure.repository;

import com.whatakitty.jmore.blog.domain.comment.Comment;
import com.whatakitty.jmore.blog.domain.comment.CommentRepository;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.comment.CommentDO;
import com.whatakitty.jmore.blog.infrastructure.repository.mybatis.comment.CommentMapper;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * comment database repository
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@Component
@RequiredArgsConstructor
public class CommentDatabaseRepository implements CommentRepository {

    private final CommentMapper commentMapper;

    private final IdDatabaseRepository idDatabaseRepository;

    @Override
    public AggregateId<Long> nextId() {
        return AggregateId.of(idDatabaseRepository.nextId());
    }

    @Override
    public void add(Comment comment) {
        CommentDO commentDO = new CommentDO();
        commentDO.setId(comment.getId().getId());
        commentDO.setCommentTime(comment.getCommentTime());
        commentDO.setContent(comment.getContent());
        commentDO.setPendingStatus(comment.getPendingStatus().getStatus().getValue());
    }

    @Override
    public void remove(Comment comment) {

    }
}
