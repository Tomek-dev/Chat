package com.example.chat.other.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUp {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
