package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.beans.factory.config.ConfigurableBeanFactory;
import com.xingfly.spring.util.ClassUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final ClassLoader beanClassLoader = ClassUtil.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    /**
     * 获取Bean
     *
     * @param name beanName
     * @return bean
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 获取Bean
     *
     * @param name Bean名称
     * @param args 参数列表
     * @return bean
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }


    /**
     * 获取Bean
     *
     * @param name  bean名称
     * @param clazz 类型
     * @return Bean实例
     */
    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 获取所有BeanPostProcessor
     *
     * @return 所有BeanPostProcessor
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 获取Bean类加载器
     *
     * @return 类加载器
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    /**
     * 定义了获取Bean的流程，具体实现交由子类实现模板方法完成
     *
     * @param name beanName
     * @param args 参数列表
     * @return bean
     */
    protected <T> T doGetBean(String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 模板方法，子类实现
        return (T) createBean(name, beanDefinition, args);
    }

    /**
     * 模板方法-通过BeanName从Bean定义注册表中获取Bean定义
     *
     * @param name BeanName
     * @return Bean定义
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    /**
     * 模板方法
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @param args           构造参数列表
     * @return Bean实例
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
