package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/forbidden")
    public String accessDenied() {
        return "forbidden";
    }
}
