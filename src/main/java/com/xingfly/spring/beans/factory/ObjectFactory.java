package com.xingfly.spring.beans.factory;


import com.xingfly.spring.beans.BeansException;

/**
 * Defines a factory which can return an Object instance

 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
