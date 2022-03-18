package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.BeanFactory;

/**
 * AutowireCapableBeanFactory
 *
 * @author supers
 * 2022/3/18
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行BeanPostProcessors的前置处理
     *
     * @param bean     bean
     * @param beanName bean名称
     * @return bean
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 执行BeanPostProcessors的后置处理
     *
     * @param bean     bean
     * @param beanName bean名称
     * @return bean
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
