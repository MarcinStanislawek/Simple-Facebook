package com.marcin.Facebook.event.notification;

import com.marcin.Facebook.invitation.service.InvitationService;
import com.marcin.Facebook.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class NotificationEventListener implements ApplicationListener<NotificationEvent> {

    private final NotificationService notificationService;

    @Autowired
    public NotificationEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(NotificationEvent notificationEvent) {
        notificationService.addNotification(notificationEvent.getUserId());
    }
}
