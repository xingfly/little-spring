package com.xingfly.spring.beans.factory;

import com.xingfly.spring.beans.BeansException;

/**
 * BeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public interface BeanFactory {
    /**
     * 获取Bean
     *
     * @param name Bean名称
     * @return bean
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取Bean
     *
     * @param name Bean名称
     * @param args 参数列表
     * @return bean
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 获取Bean
     *
     * @param name  bean名称
     * @param clazz 类型
     * @return bean
     */
    <T> T getBean(String name, Class<T> clazz) throws BeansException;

    /**
     * 获取Bean
     *
     * @param requiredType 类型
     * @return Bean
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
