package com.xingfly.spring.test;

import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xingfly.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.xingfly.spring.context.support.ClassPathXmlApplicationContext;
import com.xingfly.spring.test.bean.UserService;
import com.xingfly.spring.test.common.MyBeanFactoryPostProcessor;
import com.xingfly.spring.test.common.MyBeanPostProcessor;
import org.junit.Test;

/**
 * ApiTest
 *
 * @author supers
 * 2022/3/17
 */
public class ApiTest {


    @Test
    public void test_application() {
        // 创建应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = context.getBean("userService", UserService.class);
        userService.hello();
        System.out.println("测试结果：" + userService);
    }

}
