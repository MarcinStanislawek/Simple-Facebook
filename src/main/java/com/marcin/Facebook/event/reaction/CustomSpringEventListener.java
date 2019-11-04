package com.marcin.Facebook.event.reaction;

import com.marcin.Facebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringEventListener implements ApplicationListener<ReactionEvent> {
    private final UserService userService;

    @Autowired
    public CustomSpringEventListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ReactionEvent event) {
        userService.sendEmailToUser(event.getWho(), "Reaction added to post", event.getMessage());
        userService.sendEmailToUser(event.getToWhom(), "Someone reacted to your post", event.getMessage());
    }
}
