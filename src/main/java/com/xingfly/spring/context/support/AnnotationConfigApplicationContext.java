package com.xingfly.spring.context.support;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.support.BeanDefinitionRegistry;
import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xingfly.spring.core.io.AnnotatedBeanDefinitionReader;

/**
 * AnnotationConfigApplicationContext
 *
 * @author supers
 * 2022/3/20
 */
public class AnnotationConfigApplicationContext extends AbstractRefreshableApplicationContext {

    private final String[] packages;

    public AnnotationConfigApplicationContext(String... packages) {
        this.packages = packages;
        refresh();
    }


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        reader.register(packages);
    }
}
