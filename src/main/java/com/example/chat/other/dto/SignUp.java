package com.example.chat.other.dto;

import com.example.chat.other.validation.Email;
import com.example.chat.other.validation.Password;
import com.example.chat.other.validation.PasswordDetails;
import com.example.chat.other.validation.Username;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Password
public class SignUp implements PasswordDetails {

    @Username
    @NotBlank
    @Size(min = 8, max = 24)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 36)
    private String password;
    private String confirmPassword;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
