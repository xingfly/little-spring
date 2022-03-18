package com.xingfly.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.DisposableBean;
import com.xingfly.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁Bean接口适配器，包装bean做统一销毁操作处理
 * DisposableBeanAdapter
 *
 * @author supers
 * 2022/3/18
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("destroy method " + destroyMethodName + " not found in bean " + beanName);
            }
            destroyMethod.invoke(bean);
        }
    }
}
