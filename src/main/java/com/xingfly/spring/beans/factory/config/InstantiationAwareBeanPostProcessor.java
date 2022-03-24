package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.BeansException;

/**
 * 在CreateBean时判断
 * InstantiationAwareBeanPostProcessor
 *
 * @author supers
 * 2022/3/23
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 实例化前的后置处理（从代理工厂获取代理）
     *
     * @param beanClass 类
     * @param beanName  实例名称
     * @return obj
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
