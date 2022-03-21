package com.xingfly.spring.core.io;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.tools.javac.util.StringUtils;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValue;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanReference;
import com.xingfly.spring.beans.factory.config.ConfigurableBeanFactory;
import com.xingfly.spring.beans.factory.support.BeanDefinitionRegistry;
import com.xingfly.spring.stereotype.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * AnnotatedBeanDefinitionReader
 *
 * @author supers
 * 2022/3/20
 */
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void register(String... packages) {
        for (String pack : packages) {
            registerBean(pack);
        }
    }

    public void registerBean(String pack) {
        // 通过 Class上的属性basePackages找到需要扫描的包
        Set<Class<?>> classes = ClassUtil.scanPackage(pack);
        for (Class<?> clazz : classes) {
            // 判断是否有@Bean注解
            if (!clazz.isAnnotationPresent(Bean.class)) {
                continue;
            }
            Bean beanAnnotation = clazz.getAnnotation(Bean.class);
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            String scope = beanAnnotation.singleton() ? ConfigurableBeanFactory.SCOPE_SINGLETON : ConfigurableBeanFactory.SCOPE_PROTOTYPE;
            beanDefinition.setScope(scope);
            String beanName = StrUtil.isNotEmpty(beanAnnotation.value()) ? beanAnnotation.value() : clazz.getSimpleName();
            beanName = StrUtil.lowerFirst(beanName);
            for (Method method : clazz.getDeclaredMethods()) {
                if (null != method.getAnnotation(InitMethod.class)) {
                    beanDefinition.setInitMethodName(method.getName());
                }
                if (null != method.getAnnotation(DestroyMethod.class)) {
                    beanDefinition.setDestroyMethodName(method.getName());
                }
            }
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                PropertyValue propertyValue;
                if (null != field.getAnnotation(Value.class)) {
                    propertyValue = new PropertyValue(fieldName, field.getAnnotation(Value.class).value());
                    beanDefinition.getPropertyValues().addPropertyValue(propertyValue);

                }
                if (null != field.getAnnotation(Inject.class)) {
                    String fieldNameValue = field.getAnnotation(Inject.class).value();
                    fieldName = StrUtil.isNotEmpty(fieldNameValue) ? fieldNameValue : field.getName();
                    propertyValue = new PropertyValue(fieldName, new BeanReference(fieldName, field.getName()));
                    beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                }
            }
            if (registry.containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }


}
