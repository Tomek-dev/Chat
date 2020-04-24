package com.example.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/public")
    public String publicChat(){
        return "public";
    }

    @GetMapping("/private")
    public String privateChat(){
        return "private";
    }
}
