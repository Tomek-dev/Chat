package com.example.chat;

import com.example.chat.dao.InviteDao;
import com.example.chat.dao.UserDao;
import com.example.chat.model.Invite;
import com.example.chat.model.User;
import com.example.chat.other.builder.UserBuilder;
import com.example.chat.other.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class Start {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private InviteDao inviteDao;

    @Autowired
    public Start(UserDao userDao, PasswordEncoder passwordEncoder, InviteDao inviteDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.inviteDao = inviteDao;
        init();
    }

    private void init(){
        User[] users = new User[3];
        users[0] = UserBuilder.builder()
                .username("Woody")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .email("email")
                .build();
        users[1] = UserBuilder.builder()
                .username("BuzzLightYear")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .email("email")
                .build();
        users[2] = UserBuilder.builder()
                .username("Andy")
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton(Role.USER))
                .email("email")
                .build();
        Invite invite = Invite.builder()
                .invited(users[0])
                .inviting(users[2])
                .build();
        userDao.saveAll(Arrays.asList(users));
        inviteDao.save(invite);
        users[0].addFriend(users[1]);
        userDao.saveAll(Arrays.asList(users));
    }
}
