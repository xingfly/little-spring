package com.xingfly.spring.core.io;

/**
 * 资源读取器
 * ResourceLoader
 *
 * @author supers
 * 2022/3/18
 */
public interface ResourceLoader {

    String CLASS_PATH_PREFIX = "classpath:";

    /**
     * 将路径转换为资源
     *
     * @param location 路径
     * @return 资源
     */
    Resource getResource(String location);

}
