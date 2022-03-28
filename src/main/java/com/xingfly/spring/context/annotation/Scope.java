package com.xingfly.spring.context.annotation;

import java.lang.annotation.*;

/**
 * Scope
 *
 * @author supers
 * 2022/3/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
