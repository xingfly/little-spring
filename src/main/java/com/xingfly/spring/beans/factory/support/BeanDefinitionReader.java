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
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
