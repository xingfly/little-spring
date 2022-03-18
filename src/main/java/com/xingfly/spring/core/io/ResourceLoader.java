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

    Resource getResource(String location);

}
