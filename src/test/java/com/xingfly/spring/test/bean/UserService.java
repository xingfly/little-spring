package com.xingfly.spring.test.bean;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.*;
import com.xingfly.spring.context.ApplicationContext;
import com.xingfly.spring.context.ApplicationContextAware;

/**
 * UserService
 *
 * @author supers
 * 2022/3/17
 */
public class UserService implements InitializingBean, DisposableBean, BeanClassLoaderAware, ApplicationContextAware, BeanNameAware, BeanFactoryAware {

    private String id;

    private String company;

    private String location;

    private IUserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void hello() {
        System.out.println("Hello：" + userDao.queryUserName(id));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
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

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("BeanName：" + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
