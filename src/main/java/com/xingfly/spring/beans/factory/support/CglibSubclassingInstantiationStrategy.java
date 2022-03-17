package com.xingfly.spring.beans.factory.support;

import com.sun.tools.corba.se.idl.Noop;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * 基于Cglib实现的实例化策略
 * CglibSubclassingInstantiationStrategy
 *
 * @author supers
 * 2022/3/17
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        // 构造函数为空直接创建
        if (null == ctor) {
            return enhancer.create();
        }
        // 构造函数不为空，通过传递构造函数参数创建
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
