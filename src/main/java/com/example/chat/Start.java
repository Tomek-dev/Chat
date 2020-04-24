package com.example.chat;

import com.example.chat.dao.UserDao;
import com.example.chat.model.User;
import com.example.chat.other.builder.UserBuilder;
import com.example.chat.other.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Start {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        init();
    }

    private void init(){
        User user = UserBuilder.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .email("email")
                .build();
        userDao.save(user);
    }
}
