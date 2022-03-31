package com.xingfly.spring.test.bean;

import com.xingfly.spring.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "S，成都，1");
        hashMap.put("10002", "F，河南，2");
        hashMap.put("10003", "Z，香港，3");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
