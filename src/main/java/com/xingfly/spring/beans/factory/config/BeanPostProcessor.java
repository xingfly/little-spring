package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.BeansException;

/**
 * Spring提供的拓展机制，在Bean对象实例化之后修改Bean对象、替换Bean对象。与AOP实现有关。
 * BeanPostProcessor
 *
 * @author supers
 * 2022/3/18
 */
public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     *
     * @param bean     bean对象
     * @param beanName bean名称
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
