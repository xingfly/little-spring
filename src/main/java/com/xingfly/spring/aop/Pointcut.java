package com.xingfly.spring.aop;

/**
 * 切点 — Pointcut
 *
 * @author supers
 * 2022/3/22
 */
public interface Pointcut {
    /**
     * 获取类过滤器
     *
     * @return 类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 获取方法匹配器
     *
     * @return 方法匹配器
     */
    MethodMatcher getMethodMatcher();
}
