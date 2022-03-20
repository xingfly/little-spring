package com.xingfly.spring.context.event;

import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.context.ApplicationEvent;
import com.xingfly.spring.context.support.ApplicationListener;

/**
 * SimpleApplicationEventMulticaster
 *
 * @author supers
 * 2022/3/20
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
