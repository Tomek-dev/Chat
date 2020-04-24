package com.example.chat.service;

import com.example.chat.dao.UserDao;
import com.example.chat.model.User;
import com.example.chat.other.builder.UserBuilder;
import com.example.chat.other.dto.SignUp;
import com.example.chat.other.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    public void addUser(SignUp signUp){
        User user = UserBuilder.builder()
                .username(signUp.getUsername())
                .email(signUp.getEmail())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .roles(Collections.singleton(Role.USER))
                .build();
        userDao.save(user);
    }
}
