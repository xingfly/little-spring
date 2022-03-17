package com.xingfly.spring.beans.factory.config;

/**
 * BeanDefinition
 *
 * @author supers
 * 2022/3/17
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
