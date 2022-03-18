package com.xingfly.spring.beans.factory;

/**
 * 初始化Bean接口 - InitializingBean
 *
 * @author supers
 * 2022/3/18
 */
public interface InitializingBean {
    /**
     * Bean处理了属性填充后调用此方法
     */
    void afterPropertiesSet() throws Exception;
}
