package com.marcin.Facebook.user.model;

import com.marcin.Facebook.user.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class RegisterUserRequest {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private Sex sex;

    @NotNull
    private String email;
}
