package com.xingfly.spring;

/**
 * BeanDefinition
 *
 * @author supers
 * 2022/3/17
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
