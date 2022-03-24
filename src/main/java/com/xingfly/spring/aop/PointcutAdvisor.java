package com.xingfly.spring.aop;

/**
 * 切点顾问
 * PointcutAdvisor
 *
 * @author supers
 * 2022/3/23
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * 获取切点
     *
     * @return 切点
     */
    Pointcut getPointcut();
}
