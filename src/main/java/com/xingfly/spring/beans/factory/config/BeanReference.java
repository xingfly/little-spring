package com.xingfly.spring.beans.factory.config;

/**
 * Bean引用
 * BeanReference
 *
 * @author supers
 * 2022/3/17
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
