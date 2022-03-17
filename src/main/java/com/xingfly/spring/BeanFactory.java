package com.xingfly.spring;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanFactory
 *
 * @author supers
 * 2022/3/17
 */
public class BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(16);

    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
