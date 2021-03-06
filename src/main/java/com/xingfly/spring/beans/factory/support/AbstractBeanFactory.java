package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.FactoryBean;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.beans.factory.config.ConfigurableBeanFactory;
import com.xingfly.spring.util.ClassUtil;
import com.xingfly.spring.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final ClassLoader beanClassLoader = ClassUtil.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();


    /**
     * 获取Bean
     *
     * @param name beanName
     * @return bean
     */
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    /**
     * 获取Bean
     *
     * @param name Bean名称
     * @param args 参数列表
     * @return bean
     */
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }


    /**
     * 获取Bean
     *
     * @param name  bean名称
     * @param clazz 类型
     * @return Bean实例
     */
    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 获取所有BeanPostProcessor
     *
     * @return 所有BeanPostProcessor
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    /**
     * 获取Bean类加载器
     *
     * @return 类加载器
     */
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    /**
     * 定义了获取Bean的流程，具体实现交由子类实现模板方法完成
     *
     * @param name beanName
     * @param args 参数列表
     * @return bean
     */
    protected <T> T doGetBean(String name, final Object[] args) {
        // 是否存在单例Bean
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 检查当前实例是否是FactoryBean，如果不是直接返回Bean实例，如果是需要去调用FactoryBean的getObject()方法创建实例
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        // 模板方法，子类实现（创建Bean时会Check是否为单例模式，如果不是不加入缓存）
        Object bean = createBean(name, beanDefinition, args);
        // 检查当前实例是否是FactoryBean，如果不是直接返回Bean实例，如果是需要去调用FactoryBean的getObject()方法创建实例
        return (T) getObjectForBeanInstance(bean, name);
    }

    /**
     * getObject
     * 情况一：如果不是FactoryBean直接返回实例
     * 情况二：如果是FactoryBean则调用FactoryBean的getObject()方法
     *
     * @param beanInstance Bean实例
     * @param beanName     BeanName
     * @return Bean实例（根据条件返回情况一 or 情况二）
     */
    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // 如果不是FactoryBean直接返回实例
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        // 先查询FactoryBean创建的实例是否已经存在，存在直接返回（能查到说明创建的是单例Bean）
        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            // 如果查不到，将回去调用getObject方法创建实例
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }

    /**
     * 模板方法-通过BeanName从Bean定义注册表中获取Bean定义
     *
     * @param name BeanName
     * @return Bean定义
     */
    protected abstract BeanDefinition getBeanDefinition(String name) throws BeansException;

    /**
     * 模板方法
     *
     * @param beanName       Bean名称
     * @param beanDefinition Bean定义
     * @param args           构造参数列表
     * @return Bean实例
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }
}
