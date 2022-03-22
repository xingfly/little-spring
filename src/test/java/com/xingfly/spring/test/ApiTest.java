package com.xingfly.spring.test;

import com.xingfly.spring.aop.AdvisedSupport;
import com.xingfly.spring.aop.MethodMatcher;
import com.xingfly.spring.aop.TargetSource;
import com.xingfly.spring.aop.aspectj.AspectJExpressionPointcut;
import com.xingfly.spring.aop.springframework.Cglib2AopProxy;
import com.xingfly.spring.aop.springframework.JdkDynamicAopProxy;
import com.xingfly.spring.aop.springframework.ReflectiveMethodInvocation;
import com.xingfly.spring.context.support.ClassPathXmlApplicationContext;
import com.xingfly.spring.test.bean.UserService;
import com.xingfly.spring.test.bean.proxy.IUserService;
import com.xingfly.spring.test.bean.proxy.UserServiceImpl;
import com.xingfly.spring.test.bean.proxy.UserServiceInterceptor;
import com.xingfly.spring.test.event.CustomEvent;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ApiTest
 *
 * @author supers
 * 2022/3/17
 */
public class ApiTest {


    @Test
    public void test_application() {
        // 创建应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService1 = context.getBean("userService", UserService.class);
        UserService userService2 = context.getBean("userService", UserService.class);

        // 3. 打印十六进制哈希
        System.out.println(userService1 + " 十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
        System.out.println(userService2 + " 十六进制哈希：" + Integer.toHexString(userService2.hashCode()));

    }

    @Test
    public void test_factory_bean() {
        // 创建应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();
        UserService userService1 = context.getBean("userService", UserService.class);
        userService1.hello();
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1L, "成功了！"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserServiceImpl();

        // AOP 代理对象
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            final MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.xingfly.spring.test.bean.proxy.IUserService.queryUserInfo(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //方法匹配
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        String result = proxy.queryUserInfo();
        proxy.register("x");
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserServiceImpl();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.xingfly.spring.test.bean.proxy.IUserService.*(..))"));
        // JDK动态代理
        IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("JDK动态代理测试结果：" + jdkProxy.queryUserInfo());

        // Cglib代理
        IUserService cglibProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("CGLIB动态代理测试结果：" + cglibProxy.register("x"));

    }

}
