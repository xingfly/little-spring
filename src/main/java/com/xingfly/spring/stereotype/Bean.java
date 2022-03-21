package com.xingfly.spring.stereotype;

import java.lang.annotation.*;

/**
 * Bean
 *
 * @author supers
 * 2022/3/21
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
    String value() default "";

    boolean singleton() default true;
}
