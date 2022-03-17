package com.xingfly.spring.test.bean;

/**
 * UserService
 *
 * @author supers
 * 2022/3/17
 */
public class UserService {

    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("Hello：" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
