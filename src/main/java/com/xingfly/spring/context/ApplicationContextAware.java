package com.xingfly.spring.context;

import com.xingfly.spring.beans.factory.Aware;

/**
 * 应用上下文感知接口
 * ApplicationContextAware
 *
 * @author supers
 * 2022/3/19
 */
public interface ApplicationContextAware extends Aware {
    /**
     * 设置应用上下文
     *
     * @param applicationContext 应用上下文
     */
    void setApplicationContext(ApplicationContext applicationContext);
}
