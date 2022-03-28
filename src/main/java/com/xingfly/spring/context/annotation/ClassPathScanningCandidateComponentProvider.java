package com.xingfly.spring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 类路径扫描候选组件提供程序
 * ClassPathScanningCandidateComponentProvider
 *
 * @author supers
 * 2022/3/28
 */
public class ClassPathScanningCandidateComponentProvider {
    /**
     * 扫描包路径下，所有的Component注解的类
     *
     * @param basePackage 包路径
     * @return Set<BeanDefinition>
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        // 通过调用HuTool的ClassUtil去扫描某个包下所有包含Component注解的类
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
