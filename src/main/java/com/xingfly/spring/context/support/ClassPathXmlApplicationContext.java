package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;

/**
 * 最终实现类，通过传递classpath:spring.xml
 * 交由AbstractApplicationContext完成刷新
 * ClassPathXmlApplicationContext
 *
 * @author supers
 * 2022/3/18
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private final String[] configLocations;

    public ClassPathXmlApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        // 调用AbstractApplicationContext父类的refresh刷新方法完成容器的刷新
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 实现父类AbstractXmlApplicationContext模板方法，获得配置文件路径
     */
    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}
