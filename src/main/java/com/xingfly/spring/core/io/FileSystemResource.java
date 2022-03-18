package com.xingfly.spring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * FileSystemResource
 *
 * @author supers
 * 2022/3/18
 */
public class FileSystemResource implements Resource {
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        return new FileInputStream(file);
    }

    public String getPath() {
        return path;
    }
}
