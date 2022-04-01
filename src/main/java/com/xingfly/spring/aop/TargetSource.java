package com.xingfly.spring.aop;

import com.xingfly.spring.util.ClassUtil;

/**
 * 目标对象 - TargetSource
 *
 * @author supers
 * 2022/3/22
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtil.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
