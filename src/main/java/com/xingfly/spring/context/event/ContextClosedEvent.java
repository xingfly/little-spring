package com.xingfly.spring.context.event;

/**
 * 上下文关闭事件
 *
 * @author supers
 * 2022/3/20
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * 构建一个典型的事件。
     *
     * @param source 事件最初发生在哪个对象上。
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
