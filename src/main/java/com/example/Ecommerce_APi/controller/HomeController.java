package com.example.Ecommerce_APi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";  
    }


    @GetMapping("/admin")
    public String Admin() {
        return "admin";  
    }
}
