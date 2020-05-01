package com.controller;

import com.config.repository.UserRepository;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.service.Service;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @ModelAttribute("user") User user) {
        final String role = "ROLE_USER";
        user.setRoles(Collections.singleton());
        service.addUser(user, role);
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/")
    public String redirection() {
        return "redirect:/login";
    }
}
