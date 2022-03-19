package com.xingfly.spring.beans.factory;

/**
 * 工厂Bean
 * FactoryBean
 *
 * @author supers
 * 2022/3/19
 */
public interface FactoryBean<T> {
    /**
     * 获取Bean实例
     *
     * @return bean实例
     */
    T getObject() throws Exception;

    /**
     * 获取Bean类型
     *
     * @return Bean类型
     */
    Class<?> getObjectType();

    /**
     * 创造出来的Bean是否为单例模式
     *
     * @return result
     */
    boolean isSingleton();
}
