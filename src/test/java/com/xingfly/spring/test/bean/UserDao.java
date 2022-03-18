package com.xingfly.spring.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao
 *
 * @author supers
 * 2022/3/17
 */
public class UserDao {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1", "xf");
        map.put("2", "fpf");
        map.put("3", "wl");
    }

    public String getUserName(String id) {
        return map.get(id);
    }
}
