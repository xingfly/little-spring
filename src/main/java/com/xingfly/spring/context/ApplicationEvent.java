package com.xingfly.spring.context;

import java.util.EventObject;

/**
 * 应用事件-ApplicationEvent
 *
 * @author supers
 * 2022/3/20
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * 构建一个典型的事件。
     *
     * @param source 事件最初发生在哪个对象上。
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
