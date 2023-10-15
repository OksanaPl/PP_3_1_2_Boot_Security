package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserRepository {

    void addUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    User getUserById(Long id);

    void deleteById(Long id);

    User getUserByBankAccount(String bankAccount);
}

