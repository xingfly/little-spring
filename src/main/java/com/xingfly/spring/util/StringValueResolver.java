package com.xingfly.spring.util;

/**
 * StringValueResolver
 *
 * @author supers
 * 2022/3/29
 */
public interface StringValueResolver {
    /**
     * 解析字符串值
     *
     * @param strVal 字符串值
     * @return 字符串值
     */
    String resolveStringValue(String strVal);
}
