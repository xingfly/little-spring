package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.core.io.DefaultResourceLoader;
import com.xingfly.spring.core.io.ResourceLoader;

/**
 * 抽象Bean定义读取器
 * 完成属性初始化相关工作
 * AbstractBeanDefinitionReader
 *
 * @author supers
 * 2022/3/18
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private final ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
