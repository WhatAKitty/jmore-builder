package com.whatakitty.jmore.console.infrastructure;

import com.whatakitty.jmore.console.domain.command.CommandRepository;
import com.whatakitty.jmore.console.domain.command.ICommand;
import com.whatakitty.jmore.console.domain.command.ShowHistoryCommand;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * default command repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
public class DefaultCommandRepository implements CommandRepository {

    protected static final Map<AggregateId<String>, ICommand> holder = new ConcurrentHashMap<>(16);

    static {
        holder.put(new AggregateId<>(ShowHistoryCommand.COMMAND_TIP), new ShowHistoryCommand());
    }

    @Override
    public Optional<ICommand> findById(AggregateId<String> commandId) {
        return Optional.ofNullable(holder.get(commandId));
    }

    @Override
    public List<ICommand> findInIds(List<AggregateId<String>> commandIds) {
        return commandIds.parallelStream()
            .map(holder::get)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public boolean saveCommand(String commmandName, ICommand command) {
        return holder.putIfAbsent(new AggregateId<>(commmandName), command) == null;
    }

}
