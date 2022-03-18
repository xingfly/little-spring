package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xingfly.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 抽象XML应用上下文-AbstractXmlApplicationContext
 * 实现父类的loadBeanDefinitions模板方法，读取XML并注册为BeanDefinition
 *
 * 实现了父类的loadBeanDefinitions模板方法，读取XML并注册为BeanDefinition
 * 留出了getConfigLocations模板方法，交由子类实现，获取配置路径。
 *
 * @author supers
 * 2022/3/18
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        // 初始化XMLBeanDefinitionReader用于读取XML配置并注册BeanDefinition
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        // 获取配置文件路径
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            // 读取配置文件 & 注册到BeanDefinitionRegistry
            reader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置路径，模板方法交由子类实现
     */
    protected abstract String[] getConfigLocations();
}
