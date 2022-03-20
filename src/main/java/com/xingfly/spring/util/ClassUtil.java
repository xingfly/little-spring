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

    /**
     * 检查指定的类是否是CGLIB-生成的类。
     *
     * @param clazz 类型
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    /**
     * 检查指定的类名是否是CGLIB-生成的类。
     *
     * @param className 类名
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
