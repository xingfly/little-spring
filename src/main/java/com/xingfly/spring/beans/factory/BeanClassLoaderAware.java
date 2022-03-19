package com.xingfly.spring.beans.factory;

/**
 * Bean类加载器感知类
 * BeanClassLoaderAware
 *
 * @author supers
 * 2022/3/19
 */
public interface BeanClassLoaderAware extends Aware {
    /**
     * 设置Bean类加载器
     *
     * @param classLoader 类加载器
     */
    void setBeanClassLoader(ClassLoader classLoader);
}
