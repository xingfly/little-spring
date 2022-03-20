package com.xingfly.spring.context.support;

import com.xingfly.spring.context.ApplicationEvent;

import java.util.EventListener;

/**
 * 应用监听器
 *
 * @author supers
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 处理应用事件
     *
     * @param event 事件
     */
    void onApplicationEvent(E event);

}
