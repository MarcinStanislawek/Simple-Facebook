package com.marcin.Facebook.notification;

import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.model.UserRepository;
import com.marcin.Facebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public void addNotification(Integer userId){
        User user = userRepository.findById(userId).get();
        Notification notification = new Notification();
        user.getNotifications().add(notification);
        userRepository.save(user);
        notificationRepository.save(notification);
    }

}
