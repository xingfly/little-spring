package com.xingfly.spring.test;

import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xingfly.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.xingfly.spring.core.io.DefaultResourceLoader;
import com.xingfly.spring.test.bean.UserService;
import org.junit.Before;
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
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean定义
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.hello();

    }
}
