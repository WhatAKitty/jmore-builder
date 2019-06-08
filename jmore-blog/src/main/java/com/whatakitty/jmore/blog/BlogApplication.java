package com.whatakitty.jmore.blog;

import com.whatakitty.jmore.framework.bootstrap.JMoreApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * blog application
 *
 * @author WhatAKitty
 * @date 2019/06/09
 * @description
 **/
@SpringBootApplication(scanBasePackages = "com.whatakitty.jmore")
public class BlogApplication {

    public static void main(String[] args) {
        JMoreApplication.run(BlogApplication.class, args);
    }

}
