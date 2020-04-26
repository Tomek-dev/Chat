package com.example.chat.controller;

import com.example.chat.model.User;
import com.example.chat.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private InviteService inviteService;

    @Autowired
    public ChatController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @GetMapping("/public")
    public String publicChat(){
        return "public";
    }

    @GetMapping("/private")
    public String privateChat(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("invites", inviteService.findInvites(user));
        model.addAttribute("invitations", inviteService.findInvitations(user));
        model.addAttribute("friends", "");
        return "private";
    }
}
