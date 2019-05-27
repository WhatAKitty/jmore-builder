package com.whatakitty.jmore.blog.domain.comment;

import com.whatakitty.jmore.blog.domain.article.Article;
import com.whatakitty.jmore.blog.domain.security.User;
import java.util.Date;

/**
 * comment factory
 *
 * @author WhatAKitty
 * @date 2019/05/26
 * @description
 **/
public final class CommentFactory {

    public static final CommentFactory FACTORY = new CommentFactory();

    public Comment postComment(Article article,
                               User publisher,
                               String commentContent,
                               RubbishDetectService rubbishDetectService) {
        // basic content
        final Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setArticle(article);
        comment.setPublisher(publisher);
        comment.setCommentTime(new Date());

        // rubbish ?
        final boolean isRubbish = rubbishDetectService.rubbish(comment.getContent());
        comment.setPendingStatus(isRubbish ? CommentPendingStatus.PENDING : CommentPendingStatus.PENDED);

        return comment;
    }

}
