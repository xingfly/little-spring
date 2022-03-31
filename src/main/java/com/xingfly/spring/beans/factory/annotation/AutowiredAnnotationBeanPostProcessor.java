package com.xingfly.spring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.PropertyValues;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.BeanFactoryAware;
import com.xingfly.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xingfly.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.xingfly.spring.util.ClassUtil;

import java.lang.reflect.Field;

/**
 * 自动注入注解 BeanPostProcessor（在Bean对象实例化之后修改Bean对象、替换Bean对象。）
 * AutowiredAnnotationBeanPostProcessor
 *
 * @author supers
 * 2022/3/29
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor
        , BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }



    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        clazz = ClassUtil.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                // 使用嵌入式解析器，解析出值
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }
}
