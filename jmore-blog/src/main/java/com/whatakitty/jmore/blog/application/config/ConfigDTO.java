package com.whatakitty.jmore.blog.application.config;

import lombok.Data;

/**
 * config dto
 *
 * @author WhatAKitty
 * @date 2019/06/19
 * @description
 **/
@Data
public class ConfigDTO {

    private String title;
    private String contact;
    private String copyright;
    private String domain;
    private String admin;
    private String password;
    private String mobile;

}
