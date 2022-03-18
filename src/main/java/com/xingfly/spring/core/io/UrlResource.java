package com.xingfly.spring.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * UrlResource
 *
 * @author supers
 * 2022/3/18
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException exception) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw exception;
        }
    }
}
