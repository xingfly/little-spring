package com.xingfly.spring.test.common;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.test.bean.UserService;

/**
 * MyBeanPostProcessor
 *
 * @author supers
 * 2022/3/18
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("成都");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
