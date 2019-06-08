package com.whatakitty.jmore.blog.infrastructure.repository.mybatis.config;

import lombok.Data;

@Data
public class ConfigDO {
    private Long id;

    private String title;

    private String contactEmail;

    private String copyright;

    private String domain;
}