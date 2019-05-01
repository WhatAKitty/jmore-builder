package com.whatakitty.jmore.console.domain.command;

import java.io.Serializable;
import org.springframework.core.Ordered;

/**
 * command interface
 *
 * @author WhatAKitty
 * @date 2019/05/01
 * @description
 **/
public interface ICommand extends Ordered, Serializable {

    /**
     * get the snapshot of the current command
     *
     * @return snapshot command
     */
    ICommand get();

    /**
     * execute the command
     */
    void execute();

    /**
     * undo the command
     */
    void undo();

    /**
     * the order the commands should execute as excepted
     *
     * @return the command execute order
     */
    @Override
    default int getOrder() {
        return 0;
    }

}
