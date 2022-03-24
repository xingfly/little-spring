package com.xingfly.spring.aop.framework.autoproxy;

import com.xingfly.spring.aop.*;
import com.xingfly.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.xingfly.spring.aop.framework.ProxyFactory;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.BeanFactoryAware;
import com.xingfly.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.xingfly.spring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * DefaultAdvisorAutoProxyCreator
 *
 * @author supers
 * 2022/3/23
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 如果是Advice 或 Pointcut 或 Advisor的父类，则不进行代理
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        // 获取所有切面支持对象（包含了切点、表达式、拦截方法）对象
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        // AspectJExpressionPointcutAdvisor类的配置在XML中，会在createBean时创建
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            // 从顾问的切面中获取类过滤器
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            // 类不匹配的跳过
            if (!classFilter.matches(beanClass)) {
                continue;
            }
            // 创建切面通知
            AdvisedSupport advisedSupport = new AdvisedSupport();
            // 创建目标对象包装
            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            // 将增强方法从切面支持对象中取出，放入到切面通知中
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            // 将方法匹配起从切面支持对象的切点中取出，放入到切面通知中
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            // false-JDK动态代理、true-CGlib动态代理
            advisedSupport.setProxyTargetClass(false);
            // 从代理工厂获取代理（代理类中会被织入增强方法）
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    /**
     * 是否为Advice 或 Pointcut 或 Advisor的父类
     *
     * @param beanClass 当前类
     * @return result
     */
    private boolean isInfrastructureClass(Class<?> beanClass) {
        // isAssignableFrom从类继承的角度去判断，某个类是否是另一个类的父类
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

}
