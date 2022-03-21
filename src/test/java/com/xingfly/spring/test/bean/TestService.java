package com.xingfly.spring.test.bean;

import com.xingfly.spring.stereotype.*;

/**
 * TestService
 *
 * @author supers
 * 2022/3/21
 */
@Bean
public class TestService {

    @Value("1")
    private String id;

    @Inject("dao1")
    private TestDao dao;

    @InitMethod
    public void init() {
        System.out.println("初始化方法执行");
    }

    @DestroyMethod
    public void destroy() {
        System.out.println("销毁方法执行");
    }

    public void hello() {
        System.out.println("My Name: " + dao.queryNameById(id));
    }
}
