package com.xingfly.spring.test.bean.proxy;

import com.xingfly.spring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * UserService前置增强
 * UserServiceBeforeAdvice
 *
 * @author supers
 * 2022/3/23
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("方法拦截：" + method.getName());
    }
}
