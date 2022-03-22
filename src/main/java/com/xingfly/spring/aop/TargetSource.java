package com.xingfly.spring.aop;

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
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
