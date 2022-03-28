package com.xingfly.spring.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.support.BeanDefinitionRegistry;
import com.xingfly.spring.stereotype.Component;

import java.util.Set;

/**
 * 类路径Bean定义扫描器
 * ClassPathBeanDefinitionScanner
 *
 * @author supers
 * 2022/3/28
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 扫描指定包下的类,并注册到BeanDefinitionRegistry
     *
     * @param basePackages 包路径数组
     */
    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            // 扫描包下所有包含Component注解的类
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析出注解的作用域值（单例还是原型）
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                // 注册BeanDefinition到BeanDefinitionRegistry
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    /**
     * 确定Bean名称（主要是未写名称时，自动使用小写首字母的类名作为BeanName）
     *
     * @param beanDefinition Bean定义
     * @return BeanName
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            return StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    /**
     * 解析作用域的值
     *
     * @param beanDefinition Bean定义
     * @return 作用域值
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
}
