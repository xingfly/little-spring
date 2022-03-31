package com.xingfly.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Qualifier
 *
 * @author supers
 * 2022/3/29
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
