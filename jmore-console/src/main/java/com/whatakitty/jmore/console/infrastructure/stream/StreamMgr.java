package com.whatakitty.jmore.console.infrastructure.stream;

import java.util.Scanner;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * manager for streamming
 *
 * @author WhatAKitty
 * @date 2019/05/05
 * @description
 **/
@Component
public final class StreamMgr implements InitializingBean {

    @Getter
    private static StreamMgr INSTANCE;
    private static final Scanner innerReader = new Scanner(System.in);

    @Value("${jmore.console.tip:~}")
    private String tip;

    public void print(Object obj) {
        System.out.print(obj);
    }

    public void println(Object obj) {
        print(obj);
        System.out.println();
    }

    public Scanner reader() {
        System.out.printf("%s ", tip);
        return innerReader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        INSTANCE = this;
    }
}
