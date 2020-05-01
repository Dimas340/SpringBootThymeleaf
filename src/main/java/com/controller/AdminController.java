package com.controller;

import com.config.repository.RoleRepository;
import com.config.repository.UserRepository;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin")
    public String getUsers(ModelMap model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("get", users);
        return "get";
    }

    @PostMapping("/admin/edit/*")
    public String editPage(@ModelAttribute("user") User user, @RequestParam String role) {
        Role role1 = new Role(role);
        user.setRoles(Collections.singleton(role1));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user, ModelMap model) {
        Iterable<Role> roles = roleRepository.findAll();
        Optional<User> user1 = userRepository.findById(user.getId());
        model.addAttribute("user", user1);
        model.addAttribute("roles", roles);
        return "edit";
    }

    @GetMapping(value = "/admin/add")
    public String addPage(ModelMap model) {
        Iterable<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam String role) {//пару строк убрал
//        User users = new User(name, password);
        Role role1 = new Role(role);
        user.setRoles(Collections.singleton(role1));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/*")
    public String deleteUser(@RequestParam Long id) {
        Optional<User> users = userRepository.findById(id);
        User user = users.orElse(null);
        if (user != null) {
            userRepository.deleteById(user.getId());
        }
        return "redirect:/admin";
    }

//    @PostConstruct
//    public void init() {
//        service.init();
//    }
}
