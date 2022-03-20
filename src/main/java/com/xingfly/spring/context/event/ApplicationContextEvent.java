package com.xingfly.spring.context.event;

import com.xingfly.spring.context.ApplicationContext;
import com.xingfly.spring.context.ApplicationEvent;

/**
 * ApplicationContextEvent
 *
 * @author supers
 * 2022/3/20
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * 构建一个典型的事件。
     *
     * @param source 事件最初发生在哪个对象上。
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取应用上下文
     *
     * @return 应用上下文
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
