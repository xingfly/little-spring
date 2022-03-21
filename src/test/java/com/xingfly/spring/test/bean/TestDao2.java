package com.xingfly.spring.test.bean;

import com.xingfly.spring.stereotype.Bean;
import com.xingfly.spring.stereotype.InitMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * TestDao
 *
 * @author supers
 * 2022/3/21
 */
@Bean("dao2")
public class TestDao2 implements TestDao {
    private Map<String, String> names;

    @InitMethod
    public void init() {
        names = new HashMap<>();
        names.put("1", "SuperS");
    }

    @Override
    public String queryNameById(String id) {
        return names.get(id);
    }
}
