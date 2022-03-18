package com.xingfly.spring.test.bean;

/**
 * UserService
 *
 * @author supers
 * 2022/3/17
 */
public class UserService {

    private String id;

    private String company;

    private String location;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
