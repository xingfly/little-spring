package com.xingfly.spring.aop;

/**
 * 类过滤器 - ClassFilter
 *
 * @author supers
 * 2022/3/22
 */
public interface ClassFilter {
    /**
     * 匹配方法
     *
     * @param clazz 类
     * @return 是否匹配
     */
    boolean matches(Class<?> clazz);
}
