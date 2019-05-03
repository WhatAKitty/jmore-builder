package com.whatakitty.jmore.console.domain.command;

import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import java.util.Optional;

/**
 * command repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public interface CommandRepository {

    /**
     * find command by command id
     *
     * @param commandId command id
     * @return command instance
     */
    Optional<ICommand> findById(final AggregateId<String> commandId);

    /**
     * find command list by command ids
     *
     * @param commandIds command ids
     * @return command list
     */
    List<ICommand> findInIds(final List<AggregateId<String>> commandIds);

    /**
     * persist command into storage
     *
     * @param commmandName command name
     * @param command      command instance
     * @return {true} saved successfully, {false} saved failure
     */
    boolean saveCommand(String commmandName, ICommand command);

}
