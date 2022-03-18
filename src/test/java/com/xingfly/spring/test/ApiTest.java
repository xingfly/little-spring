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
    public void test() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean定义
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition加载完成 & Bean实例化之前，修改BeanDefinition
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改Bean属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.hello();
        System.out.println("输出：" + userService);

    }

    @Test
    public void test_application() {
        // 创建应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-processor.xml");
        // 2. 获取Bean对象调用方法
        UserService userService = context.getBean("userService", UserService.class);
        userService.hello();
        System.out.println("测试结果：" + userService);
    }
}
