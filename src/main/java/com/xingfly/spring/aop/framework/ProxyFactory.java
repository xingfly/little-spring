package com.xingfly.spring.aop.framework;

import com.xingfly.spring.aop.AdvisedSupport;

/**
 * 代理工厂（用于创建Bean的代理，在其中织入增强方法）
 * ProxyFactory
 *
 * @author supers
 * 2022/3/23
 */
public class ProxyFactory {

    // 切面通知（整合了 代理对象、是否启用Cglib代理、方法拦截器、方法匹配起）
    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        // 如果是类代理，则使用Cglib动态代理
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        // 使用JDK动态代理
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
