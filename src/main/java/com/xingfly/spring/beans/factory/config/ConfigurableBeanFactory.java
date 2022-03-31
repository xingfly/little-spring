package com.xingfly.spring.beans.factory.config;

import com.xingfly.spring.beans.factory.HierarchicalBeanFactory;
import com.xingfly.spring.util.StringValueResolver;

/**
 * ConfigurableBeanFactory
 *
 * @author supers
 * 2022/3/18
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 注册BeanPostProcessor
     *
     * @param beanPostProcessor BeanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁实现了DisposableBean的单例Beans
     */
    void destroySingletons();

    /**
     * 添加嵌入式值解析器
     *
     * @param valueResolver 解析器
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 使用嵌入式解析器，解析值
     *
     * @param value 待解析的数据
     * @return 解析后的数据
     */
    String resolveEmbeddedValue(String value);
}
