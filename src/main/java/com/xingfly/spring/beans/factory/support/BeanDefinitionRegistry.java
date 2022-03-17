package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry
 *
 * @author supers
 * 2022/3/17
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
