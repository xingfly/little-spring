package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * Spring提供的拓展机制，允许在Bean对象注册后，但未实例化之前，对BeanDefinition进行修改。
 * BeanFactoryPostProcessor
 *
 * @author supers
 * 2022/3/18
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有BeanDefinition加载完毕后，实例化Bean之前，提供BeanDefinition属性的修改机制
     *
     * @param beanFactory Bean工厂
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
