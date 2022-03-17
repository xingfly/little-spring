package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitions = new HashMap<>(16);

    /**
     * AbstractBeanFactory -> getBeanDefinition模板方法的具体实现
     * 根据beanName获取beanDefinition
     *
     * @param name beanName
     * @return BeanDefinition
     */
    @Override
    protected BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        if (beanDefinition == null) {
            throw new BeansException("No bean named " + name + " is defined");
        }
        return beanDefinition;
    }

    /**
     * 注册BeanDefinition到Bean定义注册表
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName, beanDefinition);
    }
}
