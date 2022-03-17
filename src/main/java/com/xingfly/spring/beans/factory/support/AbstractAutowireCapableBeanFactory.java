package com.xingfly.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValue;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 负责Bean的装配相关业务
 * AbstractAutowireCapableBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /***
     * AbstractBeanFactory -> createBean模板方法的具体实现
     * @param beanName beanName
     * @param beanDefinition    beanDefinition
     * @return bean
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 实例化Bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给Bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 添加到单例注册表中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 给Bean填充属性
     *
     * @param beanName       bean名称
     * @param bean           bean
     * @param beanDefinition bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            // 从Bean定义中获取所有属性对象
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            // 遍历
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                // 如果属性值是BeanReference，则获取Bean的实例
                if (value instanceof BeanReference) {
                    // A依赖B，获取B的实例化
                    BeanReference beanReference = (BeanReference) value;
                    // AbstractBeanFactory的模板方法getBean（走流程，子类有具体的实现）
                    value = getBean(beanReference.getBeanName());
                }
                // 填充属性（HuTool工具类实现）
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructor = null;
        // 通过Bean定义获取Bean的类型
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 通过Class获取所有声明的构造器列表
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            // 根据传入的参数匹配一个合适的构造器
            if (null != args && declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
                break;
            }
        }
        // 获取一个策略，执行具体的实例化流程
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
