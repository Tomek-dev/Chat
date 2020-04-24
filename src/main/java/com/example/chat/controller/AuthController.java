package com.example.chat.controller;

import com.example.chat.other.dto.SignUp;
import com.example.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("signUp", new SignUp());
        return "register";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute SignUp signUp, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.addUser(signUp);
        return "redirect:/login";
    }
}
