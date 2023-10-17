package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserByBankAccount(String bankAccount);
    void addUser(User user);
    User getUserById(Long id);
    void updateUser(User user);
    void deleteById(Long id);
    List<User> listUsers();
}
