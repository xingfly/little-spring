package com.xingfly.spring.test.event;


import com.xingfly.spring.context.event.ContextRefreshedEvent;
import com.xingfly.spring.context.support.ApplicationListener;

/**
 * 上下文刷新监听器
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
