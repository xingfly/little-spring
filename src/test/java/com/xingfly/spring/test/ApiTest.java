package com.xingfly.spring.test;

import com.xingfly.spring.context.support.AnnotationConfigApplicationContext;
import com.xingfly.spring.context.support.ClassPathXmlApplicationContext;
import com.xingfly.spring.test.bean.TestService;
import com.xingfly.spring.test.bean.UserService;
import com.xingfly.spring.test.event.CustomEvent;
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
        UserService userService1 = context.getBean("userService", UserService.class);
        UserService userService2 = context.getBean("userService", UserService.class);

        // 3. 打印十六进制哈希
        System.out.println(userService1 + " 十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
        System.out.println(userService2 + " 十六进制哈希：" + Integer.toHexString(userService2.hashCode()));

    }

    @Test
    public void test_factory_bean() {
        // 创建应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService1 = context.getBean("userService", UserService.class);
        userService1.hello();
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1L, "成功了！"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_annotated() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.xingfly.spring.test");
        context.publishEvent(new CustomEvent(context, 1L, "成功了！"));
        context.registerShutdownHook();
        TestService t = context.getBean("testService", TestService.class);
        t.hello();
    }

}
