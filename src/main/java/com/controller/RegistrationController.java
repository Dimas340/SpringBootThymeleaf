package com.controller;

import com.repository.UserRepository;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
//        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        final String role = "ROLE_USER";
        User user = new User(username, password);
        Role role1 = new Role(role);
        user.setRoles(Collections.singleton(role1));
        userRepository.save(user);
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
