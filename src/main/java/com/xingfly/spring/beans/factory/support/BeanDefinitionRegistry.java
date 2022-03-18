package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry
 *
 * @author supers
 * 2022/3/17
 */
public interface BeanDefinitionRegistry {
    /***
     * 注册Bean定义
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取Bean定义
     *
     * @param beanName bean名称
     * @return bean定义
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 是否包含Bean定义
     *
     * @param beanName bean名称
     * @return 是否包含
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取所有Bean定义名称
     *
     * @return 所有Bean定义名称
     */
    String[] getBeanDefinitionNames();
}
