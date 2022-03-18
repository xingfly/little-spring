package com.xingfly.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器实现
 * DefaultResourceLoader
 *
 * @author supers
 * 2022/3/18
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must not be null");
        // 尝试类路径资源架子啊
        if (location.startsWith(CLASS_PATH_PREFIX)) {
            return new ClassPathResource(location.substring(CLASS_PATH_PREFIX.length()));
        } else {
            try {
                // 尝试URL资源加载
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 尝试文件系统资源加载
                return new FileSystemResource(location);
            }
        }
    }
}
