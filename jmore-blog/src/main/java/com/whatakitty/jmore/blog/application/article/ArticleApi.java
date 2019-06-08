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
@RequestMapping("/articles")
public final class ArticleApi {

    private final ArticleService articleService;

    /**
     * post an article and return 201 status
     *
     * @param articleDTO article info
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.post(articleDTO);
    }

}
