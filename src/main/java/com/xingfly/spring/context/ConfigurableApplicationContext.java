package com.xingfly.spring.context;

/**
 * ConfigurableApplicationContext
 * 配置应用上下文接口-提供了refresh方法
 *
 * @author supers
 * 2022/3/18
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     */
    void refresh();
}
