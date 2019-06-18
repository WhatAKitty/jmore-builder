package com.whatakitty.jmore.blog.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * config api
 *
 * @author WhatAKitty
 * @date 2019/06/19
 * @description
 **/
@RestController
@RequestMapping(path = "/configuration")
@RequiredArgsConstructor
public class ConfigApi {

    private final ConfigService configService;

    @PostMapping(path = "/initialization")
    @ResponseStatus(HttpStatus.CREATED)
    public void init(@RequestBody ConfigDTO configDTO) {
        configService.init(configDTO);
    }

}
