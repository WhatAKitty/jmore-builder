package com.whatakitty.jmore.blog.application.article;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * article dto
 *
 * @author WhatAKitty
 * @date 2019/05/30
 * @description
 **/
@Data
public final class ArticleDTO {

    /**
     * the article id
     */
    @NotBlank(
        groups = {ArticleModifyGroup.class, ArticleDropGroup.class},
        message = "the id should not be blank"
    )
    private Long id;
    /**
     * the article title
     */
    @NotBlank(
        groups = {ArticlePostGroup.class},
        message = "the title should not be blank"
    )
    @Length(
        groups = {ArticlePostGroup.class},
        min = 5, max = 64, message = "the title's length should be between 5 and 64"
    )
    private String title;
    /**
     * the article content
     */
    @NotBlank(
        groups = {ArticlePostGroup.class},
        message = "the content should not be blank"
    )
    @Length(
        groups = {ArticlePostGroup.class},
        min = 5, message = "the content's length should be greater than 5 character"
    )
    private String content;
    /**
     * the tags that the article related
     */
    @NotEmpty(
        groups = {ArticlePostGroup.class},
        message = "there should be at least one tag marked"
    )
    private List<String> tags;
    /**
     * the types that the article related
     */
    @NotEmpty(
        groups = {ArticlePostGroup.class},
        message = "there should be at least one type marked"
    )
    private List<String> types;
    /**
     * resources used in the article
     */
    private List<String> resources;

    interface ArticlePostGroup {
    }

    interface ArticleModifyGroup {
    }

    interface ArticleDropGroup {
    }

}
