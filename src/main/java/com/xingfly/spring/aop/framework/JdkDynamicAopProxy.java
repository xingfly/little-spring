package com.xingfly.spring.aop.framework;

import com.xingfly.spring.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理 - JdkDynamicAopProxy
 *
 * @author supers
 * 2022/3/22
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 匹配切点
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            // 匹配到后，调用方法拦截器
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            // 包装了MethodInvocation反射调用
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }

}
