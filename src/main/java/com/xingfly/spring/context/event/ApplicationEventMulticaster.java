package com.xingfly.spring.context.event;


import com.xingfly.spring.context.ApplicationEvent;
import com.xingfly.spring.context.support.ApplicationListener;

/**
 * 应用事件组播器
 *
 * @author supers
 * 2022/3/20
 */
public interface ApplicationEventMulticaster {
    /**
     * 添加监听器获取所有事件通知
     *
     * @param listener 监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 删除应用监听器
     *
     * @param listener 监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 多播事件
     *
     * @param event 事件
     */
    void multicastEvent(ApplicationEvent event);
}
