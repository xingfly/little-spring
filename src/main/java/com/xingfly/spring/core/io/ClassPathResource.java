package com.xingfly.spring.core.io;

import com.xingfly.spring.util.ClassUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 类路径资源
 * ClassPathResource
 *
 * @author supers
 * 2022/3/18
 */
public class ClassPathResource implements Resource {

    private final String path;

    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtil.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws Exception {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("resource not found: " + path);
        }
        return is;
    }
}
