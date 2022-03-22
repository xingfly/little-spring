package com.xingfly.spring.test.bean.proxy;

/**
 * IUserService
 *
 * @author supers
 * 2022/3/22
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
