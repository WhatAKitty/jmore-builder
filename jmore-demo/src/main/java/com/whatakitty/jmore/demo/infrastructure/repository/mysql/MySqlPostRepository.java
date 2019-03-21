package com.whatakitty.jmore.demo.infrastructure.repository.mysql;

import com.whatakitty.jmore.demo.domain.model.post.Post;
import com.whatakitty.jmore.demo.domain.repository.IPostRepository;
import com.whatakitty.jmore.demo.infrastructure.dao.mysql.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * mysql post repository
 *
 * @author WhatAKitty
 * @date 2019/02/27
 * @description
 **/
@RequiredArgsConstructor
@Repository
public class MySqlPostRepository implements IPostRepository {

    private final PostMapper postMapper;

    @Override
    public Post query(String postId) {
        return postMapper.selectByPrimaryKey(postId);
    }

}
