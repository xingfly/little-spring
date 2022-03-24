package com.xingfly.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor
 *
 * @author supers
 * 2022/3/23
 */
public interface Advisor {
    Advice getAdvice();
}
