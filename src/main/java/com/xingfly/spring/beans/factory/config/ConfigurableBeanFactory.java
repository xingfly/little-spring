package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.factory.HierarchicalBeanFactory;

/**
 * ConfigurableBeanFactory
 *
 * @author supers
 * 2022/3/18
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 注册BeanPostProcessor
     *
     * @param beanPostProcessor BeanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁实现了DisposableBean的单例Beans
     */
    void destroySingletons();
}
