package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.PropertyValues;

/**
 * Bean定义
 * BeanDefinition
 *
 * @author supers
 * 2022/3/17
 */
public class BeanDefinition {
    // 类型
    private Class beanClass;
    // 属性值
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
