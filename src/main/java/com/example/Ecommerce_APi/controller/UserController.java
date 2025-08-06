package com.example.Ecommerce_APi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.Ecommerce_APi.model.User;
import com.example.Ecommerce_APi.repository.UserRepository;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // @GetMapping("/login")
    // public String showLoginForm() {
    //     return "login";
    // }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            System.out.println("Login attempt: " + username);
    
            User user = userRepository.findByUsername(username);
    
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                model.addAttribute("username", user.getUsername());
                return "admin";
            } else {
                System.out.println("Invalid credentials!");
                model.addAttribute("error", "Invalid credentials");
                return "index";
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            model.addAttribute("error", "User not found or server error");
            return "index";
        }
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "view-users";
    }

    @GetMapping("/add-user")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }
}
