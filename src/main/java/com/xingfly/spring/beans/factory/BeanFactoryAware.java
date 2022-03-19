package com.xingfly.spring.beans.factory;

import com.xingfly.spring.beans.BeansException;

/**
 * BeanFactory感知接口
 * BeanFactoryAware
 *
 * @author supers
 * 2022/3/19
 */
public interface BeanFactoryAware extends Aware {
    /**
     * 设置BeanFactory
     *
     * @param beanFactory Bean工厂
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
