package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.context.ApplicationContext;
import com.xingfly.spring.context.ApplicationContextAware;

/**
 * ApplicationContextAwareProcessor
 *
 * @author supers
 * 2022/3/19
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
