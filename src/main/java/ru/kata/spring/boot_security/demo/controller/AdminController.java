package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping()
    public String admin() {
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "update_user";
    }

    @PostMapping("/users/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/add")
    public String addNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new_user";
    }

    @PostMapping(value = "/users/add")
    public String createNewUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "roles") String[] roles) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("users/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "index";
    }

}
