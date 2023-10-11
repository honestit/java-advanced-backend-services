package com.epam.kupisinski.jabs.springfoundation.task3;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public class RequestCounter {

    private final AtomicLong counter = new AtomicLong(0);

    public void increment() {
        var unused = counter.incrementAndGet();
    }

    public long get() {
        return counter.get();
    }
}
