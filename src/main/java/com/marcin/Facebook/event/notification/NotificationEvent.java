package com.marcin.Facebook.event.notification;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class NotificationEvent extends ApplicationEvent {

    private Integer userId;

    public NotificationEvent(Object source, Integer userId) {
        super(source);
        this.userId = userId;
    }
}
