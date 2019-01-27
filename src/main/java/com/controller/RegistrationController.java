package com.controller;

import com.entities.Role;
import com.entities.User;
import com.enums.Roles;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistration(@ModelAttribute("user") User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        Role role1 = new Role();
        role.setUser(user);
        role.setRole(Roles.ROLE_MANAGER);
        role1.setUser(user);
        role1.setRole(Roles.ROLE_ADMIN);
        user.setRoles(Arrays.asList(role, role1));
        userService.saveUser(user);
        return "registration-form";
    }
}
