package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 获取Bean
     *
     * @param name beanName
     * @return bean
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 获取Bean
     *
     * @param name Bean名称
     * @param args 参数列表
     * @return bean
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }


    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 定义了获取Bean的流程，具体实现交由子类实现模板方法完成
     *
     * @param name beanName
     * @param args 参数列表
     * @return bean
     */
    protected <T> T doGetBean(String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 模板方法，子类实现
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
