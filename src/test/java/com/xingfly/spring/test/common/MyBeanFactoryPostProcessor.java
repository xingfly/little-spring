package com.xingfly.spring.test.common;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValue;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * MyBeanFactoryPostProcessor
 *
 * @author supers
 * 2022/3/18
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "XF"));
    }
}
