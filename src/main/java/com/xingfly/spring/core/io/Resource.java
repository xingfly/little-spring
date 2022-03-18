package com.xingfly.spring.core.io;

import java.io.InputStream;

/**
 * 资源
 * Resource
 *
 * @author supers
 * 2022/3/18
 */
public interface Resource {
    /**
     * 获取输入流
     *
     * @return 输入流
     */
    InputStream getInputStream() throws Exception;
}
