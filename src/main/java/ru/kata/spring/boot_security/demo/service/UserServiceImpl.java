package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Exception.UserNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByBankAccount(String bankAccount) {
        return userRepository.findByBankAccount(bankAccount)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + "not found"));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User updated = getUserById(user.getId());
        updated.setBankAccount(user.getBankAccount());
        updated.setFirstName(user.getFirstName());
        updated.setLastName(user.getLastName());
        updated.setPassword(getPasswordEncoder().encode(user.getPassword()));
        updated.setRoles(user.getRoles());
        userRepository.save(updated);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String bankAccount) throws UserNotFoundException {
        return userRepository.findByBankAccount(bankAccount)
                .orElseThrow(() -> new UserNotFoundException("User with bankAccount: " + bankAccount + "not found"));

    }

    private BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
