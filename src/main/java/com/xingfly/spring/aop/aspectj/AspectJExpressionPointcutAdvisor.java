package com.xingfly.spring.aop.aspectj;

import com.xingfly.spring.aop.Pointcut;
import com.xingfly.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 将切面、具体拦截方法、表达式组合成一个顾问（Advisor）对象
 * AspectExpressionPointcutAdvisor
 *
 * @author supers
 * 2022/3/23
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面（主要用于类匹配，增强方法匹配）基于表达式
    private AspectJExpressionPointcut pointcut;
    // 具体拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }



    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
