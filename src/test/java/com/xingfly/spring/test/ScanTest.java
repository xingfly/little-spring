package com.xingfly.spring.test;


import com.xingfly.spring.context.support.ClassPathXmlApplicationContext;
import com.xingfly.spring.test.bean.IUserService;

/**
 * ScanTest
 *
 * @author supers
 * 2022/3/28
 */
public class ScanTest {

    @org.junit.Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
        System.out.println("测试结果：" + userService);
    }


}
