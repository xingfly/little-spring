package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.context.ApplicationEvent;
import com.xingfly.spring.context.ConfigurableApplicationContext;
import com.xingfly.spring.context.event.ApplicationEventMulticaster;
import com.xingfly.spring.context.event.ContextClosedEvent;
import com.xingfly.spring.context.event.ContextRefreshedEvent;
import com.xingfly.spring.context.event.SimpleApplicationEventMulticaster;
import com.xingfly.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * AbstractApplicationContext-抽象应用程序上下文
 * 定义了refresh基础流程
 * 留出模板方法交由子类具体实现
 * refreshBeanFactory() - 创建Bean工厂，加载、注册BeanDefinition
 *
 * @author supers
 * 2022/3/18
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    /**
     * 应用事件群发器
     */
    private ApplicationEventMulticaster applicationEventMulticaster;


    /**
     * 刷新
     * 主流程
     * 定义了refreshBeanFactory模板方法，子类中实现BeanFactory的创建和BeanDefinition的加载、注册
     * 定义了getBeanFactory模板方法，从子类获取Bean工厂
     */
    @Override
    public void refresh() {
        // 创建BeanFactory & 加载BeanDefinition
        refreshBeanFactory();

        // 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在Bean实例化之前执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor 需要提前在其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerListeners();

        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 完成容器刷新
     */
    private void finishRefresh() {
        // 发送容器刷新完成事件
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 注册监听器
     */
    private void registerListeners() {
        // 从Bean容器中获取所有监听器
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            // 添加到事件群发器中
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 初始化应用事件群发器
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册BeanPostProcessor
     *
     * @param beanFactory bean工厂
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 在Bean实例化之前执行BeanFactoryPostProcessor
     * BeanFactoryPostProcessor可以修改BeanDefinition的属性等
     *
     * @param beanFactory bean工厂
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) throws BeansException {
        return getBeanFactory().getBean(name, clazz);
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 注册虚拟机关闭钩子
     */
    @Override
    public void registerShutdownHook() {
        // 关闭时执行close方法
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 关闭方法，销毁单例对象
     */
    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        // 销毁实现了DisposableBean的单例Beans
        getBeanFactory().destroySingletons();
    }
}
