package com.marcin.Facebook.user.controller;

import com.marcin.Facebook.user.model.RegisterUserRequest;
import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/register")
@RestController
public class RegisterController {

    private final UserService service;

    @Autowired
    public RegisterController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(@RequestBody RegisterUserRequest request) { service.createUser(request);
    }

    @GetMapping
    public List<User> getUsersList() {
        return service.retriveUsers();
    }
}
