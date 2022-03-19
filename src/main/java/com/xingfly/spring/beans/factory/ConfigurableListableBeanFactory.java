package com.xingfly.spring.beans.factory;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * ConfigurableListableBeanFactory
 *
 * @author supers
 * 2022/3/18
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 获取Bean定义
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * 提前实例化Bean单例们
     */
    void preInstantiateSingletons() throws BeansException;
}
