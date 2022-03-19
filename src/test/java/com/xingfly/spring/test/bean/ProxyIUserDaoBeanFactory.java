package com.xingfly.spring.test.bean;

import com.xingfly.spring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * ProxyBeanFactory
 *
 * @author supers
 * 2022/3/19
 */
public class ProxyIUserDaoBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> map = new HashMap<>();
            map.put("1", "xf");
            map.put("2", "fpf");
            map.put("3", "wl");
            return "你被代理了[" + method.getName() + "] ：" + map.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                handler
        );
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
