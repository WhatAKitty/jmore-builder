package com.whatakitty.jmore.blog.infrastructure.rubbishDetect;

import com.whatakitty.jmore.blog.domain.comment.RubbishDetectService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

/**
 * simple rubbish detect service
 *
 * @author WhatAKitty
 * @date 2019/06/24
 * @description
 **/
@RequiredArgsConstructor
public class SimpleRubbishDetectService implements RubbishDetectService {

    private List<Resource> resource;

    @PostConstruct
    public void init(@Value("${classpath:rubbishdicts}") Resource directory) {
        try {
            File[] files = directory.getFile().listFiles();

        } catch (IOException e) {
            throw new Error("");
        }
    }

    @Override
    public boolean rubbish(String content) {
        return false;
    }

}
