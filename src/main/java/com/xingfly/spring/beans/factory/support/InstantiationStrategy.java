package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 * InstantiationStrategy
 *
 * @author supers
 * 2022/3/17
 */
public interface InstantiationStrategy {
    /**
     * 实例化
     *
     * @param beanDefinition bean定义
     * @param beanName       bean名称
     * @param ctor           构造函数
     * @param args           构造函数参数
     * @return bean实例
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args);
}
