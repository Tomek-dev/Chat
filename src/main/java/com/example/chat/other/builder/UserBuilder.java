package com.example.chat.other.builder;

import com.example.chat.model.User;
import com.example.chat.other.enums.Role;

import java.util.Set;

public class UserBuilder {

    private User user = new User();

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder id(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder username(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder email(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder roles(Set<Role> roles){
        user.setRoles(roles);
        return this;
    }

    public User build(){
        return user;
    }
}
