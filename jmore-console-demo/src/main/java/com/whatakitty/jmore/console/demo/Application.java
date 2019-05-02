package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.domain.context.ConsoleContext;
import com.whatakitty.jmore.console.JMoreConsoleRunner;
import com.whatakitty.jmore.console.application.service.CommandService;
import com.whatakitty.jmore.framework.bootstrap.JMoreApplication;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * example console application base on jmore console
 *
 * @author WhatAKitty
 * @date 2019/04/23
 * @description
 **/
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "com.whatakitty.jmore")
public class Application implements JMoreConsoleRunner {

    private final CommandService commandService;

    @Override
    public void run(ConsoleContext context) {
        while (true) {
            context.getWriter().println("Please enter command: ");
            final String command = context.getReader().nextLine();
            try {
                commandService.execute(context, command);
            } catch (UnsupportedOperationException e) {
                log.error(e.getMessage());
            }
        }
    }

    @PreDestroy
    public void destroy() {

    }

    public static void main(String[] args) {
        JMoreApplication.run(Application.class, args);
    }

}
