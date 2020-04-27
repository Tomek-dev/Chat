package com.example.chat.controller;

import com.example.chat.model.User;
import com.example.chat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendController {

    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/friend/{id}/remove")
    public String removeFriend(@PathVariable Long id, @AuthenticationPrincipal User user){
        friendService.removeFriend(id, user);
        return "redirect:/private";
    }

    @PostMapping("/friend/{id}/add")
    public String addFriend(@PathVariable Long id, @AuthenticationPrincipal User user){
        friendService.addFriend(id, user);
        return "redirect:/private";
    }
}
