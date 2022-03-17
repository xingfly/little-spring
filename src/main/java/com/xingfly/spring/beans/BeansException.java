package com.xingfly.spring.beans;

/**
 * BeansException
 *
 * @author supers
 * 2022/3/17
 */
public class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
