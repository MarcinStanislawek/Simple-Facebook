package com.marcin.Facebook.message.service;

import com.marcin.Facebook.message.model.ReactionType;
import com.marcin.Facebook.message.model.Message;
import com.marcin.Facebook.message.model.MessageRepository;
import com.marcin.Facebook.message.model.AddMessageRequest;
import com.marcin.Facebook.email.service.EmailService;
import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.model.UserRepository;
import com.marcin.Facebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository, EmailService emailService, UserService userService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userService = userService;
    }


    public void addMessage(AddMessageRequest request, Integer authorId){
        User user = userRepository.findById(authorId).get();
        Message message = new Message();
        message.setAuthor(user);
        message.setContent(request.getMessage());
        message.setIsActive(true);
        messageRepository.save(message);
        emailService.sendEmail(user.getEmail(), "TEST", "Dziękuję za dodanie wiadomości.");
    }

    public List<Message> getActiveMessageByUserId(Integer id) {
        User user = userRepository.findById(id).get();
        return user.getMessages().stream().filter(m-> m.getIsActive().equals(Boolean.TRUE)).collect(Collectors.toList());
    }

    public void addReactionToMessage(Integer userReactionId, Integer messageId, Integer reactionId) {
        User user = userRepository.findById(userReactionId).get();
        Message message = messageRepository.findById(messageId).get();
        message.setReaction(ReactionType.values()[reactionId]);
        message.setUserReactionId(user.getId());
        messageRepository.save(message);
    }

    public void deleteMessage(Integer messageId) {
        Message messageToDelete = messageRepository.findById(messageId).get();
        messageToDelete.setIsActive(false);
        messageRepository.save(messageToDelete);
    }
}
