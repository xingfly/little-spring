package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBeanRegistrySupport
 *
 * @author supers
 * 2022/3/19
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 查询缓存中是否已经存在FactoryBean创建的Bean实例
     *
     * @param beanName Bean名称
     * @return FactoryBean创建的Bean实例
     */
    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object o = this.factoryBeanObjectCache.get(beanName);
        return (o != NULL_OBJECT ? o : null);
    }

    /**
     * 调用FactoryBean的getObject方法，调用时检查FactoryBean创建的Bean是否为单例模式，如果是就放入缓存中
     * 不是说明是原型模式，直接调用创建方法返回Bean实例
     *
     * @param factory  FactoryBean
     * @param beanName Bean名称
     * @return FactoryBean创建的Bean实例（可能是缓存的）
     */
    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        // FactoryBean创造的Bean是否是单例模式
        if (factory.isSingleton()) {
            // 先从缓存中获取
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                // 获取不到，则创建
                object = doGetObjectFromFactoryBean(factory, beanName);
                // 放入缓存中
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return object != NULL_OBJECT ? object : null;
        } else {
            // 如果不是单例模式，则每次都调用BeanFactory的getObject()方法创建一个新的Bean对象
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    /**
     * 调用此方法执行FactoryBean的getObject()方法创建Bean实例
     *
     * @param factory  FactoryBean
     * @param beanName Bean名称
     * @return FactoryBean创建的Bean实例
     */
    private Object doGetObjectFromFactoryBean(FactoryBean factory, String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean throw exception on object [" + beanName + "] creation", e);
        }
    }
}
