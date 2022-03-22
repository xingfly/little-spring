package com.xingfly.spring.aop.aspectj;

import com.xingfly.spring.aop.ClassFilter;
import com.xingfly.spring.aop.MethodMatcher;
import com.xingfly.spring.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 切点表达式 —— AspectJExpressionPointcut
 *
 * @author supers
 * 2022/3/22
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    // 切点表达式对象
    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        // 切点解析器
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        // 解析切点表达式
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }

    @Override
    public boolean matches(Class<?> clazz) {
        // 表达式能否在类型上匹配切入点
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> clazz) {
        // 表达式能否匹配方法执行
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
