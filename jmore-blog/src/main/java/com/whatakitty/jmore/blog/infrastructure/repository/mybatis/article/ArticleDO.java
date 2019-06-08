package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.article;

import java.util.Date;
import lombok.Data;

@Data
public class ArticleDO {
    private Long id;

    private Date gmtCreate;

    private Long gmtCreator;

    private Date gmtModified;

    private Long gmtModifier;

    private String title;

    private String content;

    private Date publishDate;

    private Long author;

    private Integer status;

}