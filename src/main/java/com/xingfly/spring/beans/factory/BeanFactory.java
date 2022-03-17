package com.xingfly.spring.beans.factory;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

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
}
