package com.marcin.Facebook.user.service;

import com.marcin.Facebook.email.service.EmailService;
import com.marcin.Facebook.user.model.LoginUserRequest;
import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.model.RegisterUserRequest;
import com.marcin.Facebook.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

    private static final String CHAR_LIST =
            "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private User userToValidate;
    @Autowired
    private UserRepository userRepository;
    private EmailService emailService;

    public void createUser(RegisterUserRequest request) {
        String password = getRandomPassword(4);
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(password);
        user.setSex(request.getSex());
        userRepository.save(user);
    }

    public String getRandomPassword(Integer passwordLength) {
        StringBuffer randomPassword = new StringBuffer(4);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < passwordLength; i++)
            randomPassword.append(CHAR_LIST.charAt(secureRandom.nextInt(CHAR_LIST.length())));
        return randomPassword.toString();
    }

    public List<User> retriveUsers() {
        return userRepository.findAll();
    }

    public User validateUser(LoginUserRequest request) {
        userToValidate = retriveUsers().stream().filter(c -> c.getEmail().equals(request.getEmail())).findAny().get();
        if (userToValidate.getPassword().equals(request.getPassword())) {
            return userToValidate;
        }
        return null;
    }

    public String getUserEmailById(Integer id) {
        User user = userRepository.findById(id).get();
        return user.getEmail();
    }

    public void sendEmailToUser(Integer userId, String subject, String text){
        String email = userRepository.findById(userId).get().getEmail();
        emailService.sendEmail(email, subject, text);
    }
}

