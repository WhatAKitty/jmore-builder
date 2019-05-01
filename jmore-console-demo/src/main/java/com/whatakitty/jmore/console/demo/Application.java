package com.whatakitty.jmore.console.demo;

import com.whatakitty.jmore.console.ConsoleContext;
import com.whatakitty.jmore.console.JMoreConsoleRunner;
import com.whatakitty.jmore.console.domain.command.CommandFactory;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.framework.bootstrap.JMoreApplication;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CommandFactory commandFactory;

    @Override
    public void run(ConsoleContext context) {
        System.out.println("Please enter command:");
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            final String cmd = scanner.nextLine();
            ICommand command = commandFactory.create(context, Arrays.asList(cmd));
            command.execute();
        }
    }

    public static void main(String[] args) {
        JMoreApplication.run(Application.class, args);
    }

}
