package com.xingfly.spring.beans.factory;

import java.util.Map;

/**
 * 涉及Bean和BeanDefinition批量操作的接口
 * ListableBeanFactory
 *
 * @author supers
 * 2022/3/18
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 按照类型返回Bean实例
     *
     * @param type 类型
     * @return Map<String, T>
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);

    /**
     * 获取所有Bean定义名称
     *
     * @return Bean定义名称数组
     */
    String[] getBeanDefinitionNames();
}
