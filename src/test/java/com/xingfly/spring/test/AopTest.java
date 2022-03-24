package com.xingfly.spring.test;

import com.xingfly.spring.context.support.ClassPathXmlApplicationContext;
import com.xingfly.spring.test.bean.proxy.IUserService;
import org.junit.Test;

/**
 * ApiTest
 *
 * @author supers
 * 2022/3/17
 */
public class AopTest {


    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
