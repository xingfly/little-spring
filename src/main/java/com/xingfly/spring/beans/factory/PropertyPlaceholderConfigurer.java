package com.xingfly.spring.beans.factory;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValue;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.config.BeanDefinition;
import com.xingfly.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.xingfly.spring.core.io.DefaultResourceLoader;
import com.xingfly.spring.core.io.Resource;

import java.util.Properties;

/**
 * 实现了BeanFactoryPostProcessor接口，在bean实例化之前调用，可以修改BeanDefinition的属性
 * 这里通过在XML中定义PropertyPlaceholderConfigurer，并配置location属性实现注册
 * 执行postProcessBeanFactory方法,将配置文件中的值写入到BeanDefinition中
 * PropertyPlaceholderConfigurer
 *
 * @author supers
 * 2022/3/28
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            // 1.从location中读取Properties配置文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            //  2.获取所有Bean定义
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                // 3.从BeanDefinition中获取PropertyValues
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    // 如果存在需要填充的占位符
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        // 截取占位符的名称即Key
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        // 通过Key获取Value
                        String propVal = properties.getProperty(propKey);
                        // 替换掉原来的值
                        buffer.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (Exception e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
