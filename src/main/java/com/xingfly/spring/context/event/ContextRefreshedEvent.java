package com.xingfly.spring.context.event;

/**
 * ContextRefreshedEvent
 *
 * @author supers
 * 2022/3/20
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * 构建一个典型的事件。
     *
     * @param source 事件最初发生在哪个对象上。
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
