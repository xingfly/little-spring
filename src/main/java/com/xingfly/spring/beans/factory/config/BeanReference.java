package com.xingfly.spring.beans.factory.config;

/**
 * Bean引用
 * BeanReference
 *
 * @author supers
 * 2022/3/17
 */
public class BeanReference {
    // 通过BeanName获取bean
    private final String beanName;
    // 注入时，Service实际的字段名称
    private final String fieldName;

    public BeanReference(String beanName, String fieldName) {
        this.beanName = beanName;
        this.fieldName = fieldName;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
