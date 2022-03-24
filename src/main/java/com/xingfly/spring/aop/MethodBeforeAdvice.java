package com.xingfly.spring.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * MethodBeforeAdvice
 *
 * @author supers
 * 2022/3/23
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    /**
     * 前置通知
     *
     * @param method 方法
     * @param args   参数
     * @param target 目标对象
     */
    void before(Method method, Object[] args, Object target);
}
