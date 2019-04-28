package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.JMoreConsoleRunner;
import com.whatakitty.jmore.framework.bootstrap.JMoreApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * example console application base on jmore console
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
@SpringBootApplication(scanBasePackages = "com.whatakitty.jmore")
public class Application implements JMoreConsoleRunner {

    @Override
    public void run(ConsoleContext context) {
        System.out.println("test");
    }

    public static void main(String[] args) {
        JMoreApplication.run(Application.class, args);
    }

}
