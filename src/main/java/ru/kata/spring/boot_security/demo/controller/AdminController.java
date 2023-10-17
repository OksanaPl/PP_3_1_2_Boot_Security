package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.userdetails.UserDetailsImpl;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping
    public String getAllUsers(Model model,
                        Authentication authentication) {
        model.addAttribute("users", userService.listUsers());
        var user = authentication.getPrincipal();
        Long id = ((UserDetailsImpl) user).getUser().getId();
        model.addAttribute("userA", userService.getUserById(id));
        model.addAttribute("userC", new User());
        return "index";
    }

    @PostMapping
    public String create(@ModelAttribute("userC") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

}
