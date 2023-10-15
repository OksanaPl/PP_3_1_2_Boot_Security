package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UsersController {

    private UserService userService;

    @GetMapping
    public String userInfo(Principal principal, Model model) {
        User user = userService.getUserByBankAccount(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

}
