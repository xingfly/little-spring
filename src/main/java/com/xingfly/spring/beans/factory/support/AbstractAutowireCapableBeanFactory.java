package com.xingfly.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValue;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.DisposableBean;
import com.xingfly.spring.beans.factory.InitializingBean;
import com.xingfly.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 负责Bean的装配相关业务
 * AbstractAutowireCapableBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * Bean实例化策略
     */
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
            // 执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 注册实现了DisposableBean接口的Bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        // 添加到单例注册表中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 注册实现了DisposableBean接口的Bean
     *
     * @param beanName Bean名称
     * @param bean Bean
     * @param beanDefinition Bean定义
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            // 使用适配器进行包装，进行统一的销毁处理
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * 初始化Bean涉及到的具体流程
     * 调用初始化方法前和后执行BeanPostProcessors的前置处理方法和后置处理方法
     *
     * @param beanName       Bean名称
     * @param bean           Bean
     * @param beanDefinition Bean定义
     * @return 初始化后的Bean
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行BeanPostProcessor 前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 调用初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean '" + beanName + "' failed", e);
        }
        // 执行BeanPostProcessor 后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 调用初始化方法
     *
     * @param beanName       Bean名称
     * @param bean           Bean
     * @param beanDefinition Bean定义
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2. 注解配置 init-method (判断是为了避免二次执行初始化)
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
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

    /***
     * 创建Bean实例
     * @param beanDefinition Bean定义
     * @param beanName Bean名称
     * @param args 构造参数列表
     * @return Bean实例
     */
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
