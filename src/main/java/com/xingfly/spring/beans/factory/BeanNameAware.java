package com.xingfly.spring.beans.factory;

/**
 * Bean名称感知接口
 * BeanNameAware
 *
 * @author supers
 * 2022/3/19
 */
public interface BeanNameAware extends Aware {
    /**
     * 设置Bean名称
     *
     * @param beanName Bean名称
     */
    void setBeanName(String beanName);
}
