package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyThymeleaf {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("message", "스프링부트 타임리프 화면");
        return "hello_thymeleaf";
    }
    @GetMapping("/mustache")
    public String mustache(Model model) {
        model.addAttribute("message", "Mustache 화면");
        return "hello_mu";
    }
}
