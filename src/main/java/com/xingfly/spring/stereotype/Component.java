package com.xingfly.spring.stereotype;

import java.lang.annotation.*;

/**
 * Component
 *
 * @author supers
 * 2022/3/28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
