package com.example.chat.controller;

import com.example.chat.model.User;
import com.example.chat.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InviteController {

    private InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PostMapping("/invite")
    public void add(@RequestParam String username, @AuthenticationPrincipal User user){
        inviteService.add(username, user);
    }

    @DeleteMapping("/invite/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User user){
        inviteService.delete(id, user);
    }
}
