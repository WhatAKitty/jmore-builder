package com.whatakitty.jmore.demo;

import com.whatakitty.jmore.framework.bootstrap.JMoreApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jmore demo app
 *
 * @author WhatAKitty
 * @date 2019/02/19
 * @description
 **/
@SpringBootApplication(scanBasePackages = "com.whatakitty.jmore")
public class JMoreDemo {

    public static void main(String[] args) {
        JMoreApplication.run(JMoreDemo.class, args);
    }

}
