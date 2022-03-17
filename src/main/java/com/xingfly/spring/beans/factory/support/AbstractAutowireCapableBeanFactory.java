package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    /***
     * AbstractBeanFactory -> createBean模板方法的具体实现
     * @param beanName beanName
     * @param beanDefinition    beanDefinition
     * @return bean
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
