package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.core.io.Resource;
import com.xingfly.spring.core.io.ResourceLoader;

/**
 * Bean定义读取器接口
 * BeanDefinitionReader
 *
 * @author supers
 * 2022/3/18
 */
public interface BeanDefinitionReader {
    /**
     * 获取Bean定义注册表
     *
     * @return 注册表
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     *
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 加载Bean定义
     *
     * @param resource 资源
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 加载Bean定义
     *
     * @param resources 资源数组
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /***
     * 加载Bean定义
     * @param location 路径
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 加载Bean定义
     *
     * @param locations 路径数组
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
