package com.xingfly.spring.test.event;


import com.xingfly.spring.context.event.ContextClosedEvent;
import com.xingfly.spring.context.support.ApplicationListener;
import com.xingfly.spring.stereotype.Bean;

/**
 * 上下文关闭事件监听器
 */
@Bean
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
