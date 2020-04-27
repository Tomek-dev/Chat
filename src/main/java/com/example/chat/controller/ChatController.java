package com.example.chat.controller;

import com.example.chat.model.User;
import com.example.chat.service.FriendService;
import com.example.chat.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private InviteService inviteService;
    private FriendService friendService;

    @Autowired
    public ChatController(InviteService inviteService, FriendService friendService) {
        this.inviteService = inviteService;
        this.friendService = friendService;
    }

    @GetMapping("/public")
    public String publicChat(){
        return "public";
    }

    @GetMapping("/private")
    public String privateChat(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("invites", inviteService.findInvites(user));
        model.addAttribute("invitations", inviteService.findInvitations(user));
        model.addAttribute("friends", friendService.findFriends(user));
        return "private";
    }
}
