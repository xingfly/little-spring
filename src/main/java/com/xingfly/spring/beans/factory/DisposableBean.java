package com.xingfly.spring.beans.factory;

/**
 * 销毁接口
 * DisposableBean
 *
 * @author supers
 * 2022/3/18
 */
public interface DisposableBean {
    /**
     * 销毁
     */
    void destroy() throws Exception;
}
