package com.xingfly.spring.util;

/**
 * ClassUtil
 *
 * @author supers
 * 2022/3/18
 */
public class ClassUtil {
    /**
     * 获取默认资源加载器
     *
     * @return 资源加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        if (cl == null) {
            cl = ClassUtil.class.getClassLoader();
        }
        return cl;
    }
}
