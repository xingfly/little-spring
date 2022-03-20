package com.xingfly.spring.context.event;

import com.xingfly.spring.context.ApplicationEvent;

/**
 * 应用事件发布者
 * ApplicationEventPublisher
 *
 * @author supers
 * 2022/3/20
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     *
     * @param event 事件
     * @author supers
     */
    void publishEvent(ApplicationEvent event);
}
