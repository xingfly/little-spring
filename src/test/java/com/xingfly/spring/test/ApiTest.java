package com.xingfly.spring.test;

import com.xingfly.spring.BeanDefinition;
import com.xingfly.spring.BeanFactory;
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
        BeanFactory beanFactory = new BeanFactory();
        BeanDefinition userServiceBeanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.hello();
    }
}
