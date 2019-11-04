package com.marcin.Facebook.user.controller;

import com.marcin.Facebook.user.model.LoginUserRequest;
import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/login")
@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User validateUser(@RequestBody LoginUserRequest request) {
        return userService.validateUser(request);
    }
}
