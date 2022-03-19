package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.xingfly.spring.beans.factory.config.BeanPostProcessor;
import com.xingfly.spring.context.ConfigurableApplicationContext;
import com.xingfly.spring.core.io.DefaultResourceLoader;

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

        // 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
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
        // 销毁实现了DisposableBean的单例Beans
        getBeanFactory().destroySingletons();
    }
}
