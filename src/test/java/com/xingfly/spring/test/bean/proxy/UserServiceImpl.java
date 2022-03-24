package com.xingfly.spring.test.bean.proxy;

import java.util.Random;

/**
 * UserServiceImpl
 *
 * @author supers
 * 2022/3/22
 */
public class UserServiceImpl implements IUserService {

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "S，1，成都";
    }

}
