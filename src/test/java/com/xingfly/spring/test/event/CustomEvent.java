package com.xingfly.spring.test.event;

import com.xingfly.spring.context.event.ApplicationContextEvent;

/**
 * 自定义事件
 *
 * @author supers
 * 2022/3/20
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
