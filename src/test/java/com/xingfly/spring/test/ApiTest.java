package com.xingfly.spring.test;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xingfly.spring.test.bean.UserService;
import org.junit.Test;

/**
 * ApiTest
 *
 * @author supers
 * 2022/3/17
 */
public class ApiTest {
    @Test
    public void test() {
        // 创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // Bean定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 获取一个通过createBean方法创建的Bean
        UserService userService = (UserService) beanFactory.getBean("userService","S");
        userService.hello();

    }
}
