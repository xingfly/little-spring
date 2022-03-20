package com.xingfly.spring.beans.factory.config;

/**
 * 单例Bean注册表
 * SingletonBeanRegistry
 *
 * @author supers
 * 2022/3/17
 */
public interface SingletonBeanRegistry {
    /**
     * 获取单例Bean
     *
     * @param beanName bean名称
     * @return bean
     */
    Object getSingleton(String beanName);

    /***
     * 注册单例
     * @param beanName bean名称
     * @param singletonObject 单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);

}
