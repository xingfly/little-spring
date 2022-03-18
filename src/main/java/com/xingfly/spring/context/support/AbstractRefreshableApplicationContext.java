package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * 抽象可刷新应用上下文 - AbstractRefreshableApplicationContext
 * 定义了refreshBeanFactory()方法，约定了刷新步骤
 * 留出了loadBeanDefinitions模板方法，交由子类具体实现-加载XML、注册BeanDefinition
 *
 * @author supers
 * 2022/3/18
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        // 创建Bean工厂
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载Bean定义，模板方法-交给AbstractXmlApplicationContext实现
        // 会通过XmlBeanDefinitionReader去加载xml并将BeanDefinition注册到BeanDefinitionRegistry中
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 加载BeanDefinitions
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    /***
     * 创建Bean工厂
     * @return bean工厂
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
