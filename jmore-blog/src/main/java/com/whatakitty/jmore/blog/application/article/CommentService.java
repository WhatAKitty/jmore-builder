package com.whatakitty.jmore.blog.application.article;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.article.ArticleRepository;
import com.whatakitty.jmore.blog.domain.comment.*;
import com.whatakitty.jmore.blog.domain.security.User;
import com.whatakitty.jmore.blog.domain.security.UserRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * comment service
 *
 * @author WhatAKitty
 * @date 2019/06/23
 * @description
 **/
@Service
@RequiredArgsConstructor
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RubbishDetectService rubbishDetectService;

    /**
     * comment at article
     *
     * @param articleId
     * @param commentDTO
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void commentAtArticle(Long articleId, CommentDTO commentDTO) {
        final Article article = articleRepository.at(AggregateId.of(articleId));
        final User currentUser = userRepository.currentUser();
        final AggregateId<Long> commentId = commentRepository.nextId();
        final Comment comment = CommentFactory.FACTORY.postComment(
            commentId,
            CommentableResource.of(article.getId()),
            currentUser,
            commentDTO.getContent(),
            rubbishDetectService
        );
        commentRepository.add(comment);
    }

}
