package com.controller;

import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.service.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private Service service;

    @GetMapping("/admin")
    public String getUsers(ModelMap model) {
        List<User> users = service.getAllUser();
        model.addAttribute("get", users);
        return "get";
    }

    @PostMapping("/admin/edit/*")
    public String editPage(@ModelAttribute("user") User user, @RequestParam String role) {
        service.editUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user, ModelMap model) {
        List<Role> roles = service.getAllRole();
        User user1 = service.getById(user.getId());
        model.addAttribute("user", user1);
        model.addAttribute("roles", roles);
        return "edit";
    }

    @GetMapping(value = "/admin/add")
    public String addPage(ModelMap model) {
        List<Role> roles = service.getAllRole();
        model.addAttribute("roles", roles);
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam String role) {//пару строк убрал
//        User users = new User(name, password);
        service.addUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/*")
    public String deleteUser(@RequestParam Long id) {
        User users = service.getById(id);
        if (users != null) {
            service.deleteUser(users);
        }
        return "redirect:/admin";
    }

    @PostConstruct
    public void init() {
        service.init();
    }
}
