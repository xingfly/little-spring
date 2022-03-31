package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValues;

/**
 * 感知实例化Bean后置处理器
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

    /**
     * 实例化后调用
     *
     * @param bean     Bean
     * @param beanName Bean名称
     * @return result
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;


    /**
     * 处理属性值
     *
     * @param pvs      配置属性
     * @param bean     实例
     * @param beanName 实例名称
     * @return 配置属性
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;


}
