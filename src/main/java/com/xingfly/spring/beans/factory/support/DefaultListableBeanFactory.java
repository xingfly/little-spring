package com.xingfly.spring.beans.factory.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认列表Bean工厂 - DefaultListableBeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private final Map<String, BeanDefinition> beanDefinitions = new HashMap<>(16);

    /**
     * AbstractBeanFactory -> getBeanDefinition模板方法的具体实现
     * 根据beanName获取beanDefinition
     *
     * @param name beanName
     * @return BeanDefinition
     */
    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitions.get(name);
        if (beanDefinition == null) {
            throw new BeansException("No bean named " + name + " is defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitions.keySet().forEach(this::getBean);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitions.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> result = new HashMap<>();
        beanDefinitions.forEach((beanName, beanDefinition) -> {
            Class clazz = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(clazz)) {
                result.put(beanName, getBean(beanName, type));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitions.keySet().toArray(new String[0]);
    }

    /**
     * 注册BeanDefinition到Bean定义注册表
     *
     * @param beanName       beanName
     * @param beanDefinition beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName, beanDefinition);
    }

}
