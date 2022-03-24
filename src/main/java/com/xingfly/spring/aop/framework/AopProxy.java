package com.xingfly.spring.aop.framework;

/**
 * AopProxy
 *
 * @author supers
 * 2022/3/22
 */
public interface AopProxy {
    /**
     * 获取代理对象
     *
     * @return 代理对象
     */
    Object getProxy();
}
