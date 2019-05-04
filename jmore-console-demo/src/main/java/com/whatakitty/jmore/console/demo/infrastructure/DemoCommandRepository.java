package com.whatakitty.jmore.console.demo.infrastructure;

import com.whatakitty.jmore.console.demo.domain.command.SayHelloWorldCommand;
import com.whatakitty.jmore.console.infrastructure.repository.DefaultCommandRepository;
import com.whatakitty.jmore.framework.ddd.publishedlanguage.AggregateId;
import org.springframework.stereotype.Component;

/**
 * demo command repository
 *
 * @author WhatAKitty
 * @date 2019/05/03
 * @description
 **/
@Component
public class DemoCommandRepository extends DefaultCommandRepository {

    @Override
    protected void init() {
        super.init();
        put(new AggregateId<>(SayHelloWorldCommand.COMMAND_TIP), new SayHelloWorldCommand());
    }
    
}
