package com.xingfly.spring.aop.framework;

import com.xingfly.spring.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib2AopProxy
 *
 * @author supers
 * 2022/3/22
 */
public class Cglib2AopProxy implements AopProxy {
    // 切面支持对象
    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        // 切面支持对象
        private final AdvisedSupport advised;

        // 构造器传入切面支持对象
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // cglib方法执行 包装对象
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 判断切面通知 判断方法是否匹配
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getClass())) {
                // 调用拦截器的invoke方法
                org.aopalliance.intercept.MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
                // 调用MethodBeforeAdviceInterceptor中的invoke方法，该类会调用MethodBeforeAdvice的before方法，然后调用代理对象的实际方法，完成前置增强
                return methodInterceptor.invoke(methodInvocation);
            }
            // 匹配不上调用代理方法执行
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }
}
