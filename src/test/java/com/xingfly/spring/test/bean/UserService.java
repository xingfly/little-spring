package com.xingfly.spring.test.bean;

/**
 * UserService
 *
 * @author supers
 * 2022/3/17
 */
public class UserService {

    private String id;

    private UserDao userDao;


    public void hello() {
        System.out.println("Hello：" + userDao.getUserName(id));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
