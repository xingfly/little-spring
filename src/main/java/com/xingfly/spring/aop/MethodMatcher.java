package com.xingfly.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配 - MethodMatcher
 *
 * @author supers
 * 2022/3/22
 */
public interface MethodMatcher {
    /**
     * 判断方法是否匹配
     *
     * @param method 方法
     * @param clazz  类
     * @return 是否匹配
     */
    boolean matches(Method method, Class<?> clazz);
}
