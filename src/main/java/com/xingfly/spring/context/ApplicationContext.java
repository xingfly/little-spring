package com.xingfly.spring.context;

import com.xingfly.spring.beans.factory.HierarchicalBeanFactory;
import com.xingfly.spring.beans.factory.ListableBeanFactory;
import com.xingfly.spring.context.event.ApplicationEventPublisher;
import com.xingfly.spring.core.io.ResourceLoader;

/**
 * 应用程序上下文
 * ApplicationContext
 *
 * @author supers
 * 2022/3/18
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
