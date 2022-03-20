package com.xingfly.spring.context.event;

import com.xingfly.spring.beans.BeansException;
import com.xingfly.spring.beans.factory.BeanFactory;
import com.xingfly.spring.beans.factory.BeanFactoryAware;
import com.xingfly.spring.context.ApplicationEvent;
import com.xingfly.spring.context.support.ApplicationListener;
import com.xingfly.spring.util.ClassUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 抽象应用事件组播器 - AbstractApplicationEventMulticaster
 *
 * @author supers
 * 2022/3/20
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    private BeanFactory beanFactory;

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 根据事件获取对应的监听器
     *
     * @param event 事件
     * @return 对应的监听器
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEventType(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否支持事件类型
     *
     * @param applicationListener 监听器
     * @param event               事件类型
     * @return 是否支持
     */
    protected boolean supportsEventType(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        // 监听器类型
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtil.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 泛型接口
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        // 实际类型参数
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        // 实际类型参数的类名
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            // 根据实际类型参数类名，获取事件类
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name:" + className);
        }
        // 判断 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。
        // 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
