package com.whatakitty.jmore.blog.application.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * blog controller
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/articles")
public final class ArticleApi {

    private final ArticleService articleService;
    private final CommentService commentService;

    /**
     * save an article
     *
     * @param articleDTO
     */
    @PostMapping(path = "/draft")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDraft(@RequestBody ArticleDTO articleDTO) {
        articleService.draft(articleDTO);
    }

    /**
     * post an article and return 201 status
     *
     * @param articleDTO article info
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.saveAndPost(articleDTO);
    }

    /**
     * publish draft
     *
     * @param articleId
     */
    @PatchMapping(path = "/{articleId}/published")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void publish(@PathVariable("articleId") Long articleId) {
        articleService.post(articleId);
    }

    /**
     * update an article
     *
     * @param articleId
     * @param articleDTO
     */
    @PutMapping(path = "/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("articleId") Long articleId, @RequestBody ArticleDTO articleDTO) {
        articleDTO.setId(articleId);
        articleService.modify(articleDTO);
    }

    /**
     * delete an article
     *
     * @param articleId
     */
    @DeleteMapping(path = "/${articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destory(@PathVariable("articleId") Long articleId) {
        articleService.drop(articleId);
    }

    /**
     * comment at article
     *
     * @param articleId
     * @param commentDTO
     */
    @PostMapping(path = "/${articleId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void comment(@PathVariable("articleId") Long articleId, @RequestBody CommentDTO commentDTO) {
        commentService.commentAtArticle(articleId, commentDTO);
    }

}
