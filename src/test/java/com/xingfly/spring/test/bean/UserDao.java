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

    public void initData() {
        System.out.println("执行：init-method");
        map.put("1", "xf");
        map.put("2", "fpf");
        map.put("3", "wl");
    }

    public void destroyDataMethod() {
        System.out.println("执行：destroy-method");
        map.clear();
    }

    public String getUserName(String id) {
        return map.get(id);
    }
}
