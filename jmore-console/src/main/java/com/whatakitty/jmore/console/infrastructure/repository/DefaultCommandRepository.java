package com.whatakitty.jmore.console.infrastructure.repository;

import com.whatakitty.jmore.console.domain.command.CommandRepository;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.ShowHistoryCommand;
import com.whatakitty.jmore.console.domain.command.UnDoCommand;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * default command repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public class DefaultCommandRepository extends InMemoryRepository<String, ICommand> implements CommandRepository {

    @Override
    public Optional<ICommand> findById(AggregateId<String> commandId) {
        return Optional.ofNullable(get(commandId));
    }

    @Override
    public List<ICommand> findInIds(List<AggregateId<String>> commandIds) {
        return commandIds.parallelStream()
            .map(this::get)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public boolean saveCommand(String commmandName, ICommand command) {
        return putIfAbsent(AggregateId.of(commmandName), command) == null;
    }

    @Override
    protected void init() {
        AggregateId<String> showHistoryId = AggregateId.of(ShowHistoryCommand.COMMAND_TIP);
        AggregateId<String> undoHistoryId = AggregateId.of(UnDoCommand.COMMAND_TIP);
        put(showHistoryId, new ShowHistoryCommand(showHistoryId));
        put(undoHistoryId, new UnDoCommand(undoHistoryId));
    }
}
