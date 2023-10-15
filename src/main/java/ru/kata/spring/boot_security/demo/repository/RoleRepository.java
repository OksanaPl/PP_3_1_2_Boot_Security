package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleRepository {
    Set<Role> getAllRoles();

    Role getRoleByName(String name);

    Set<Role> getSetOfRoles(String[] roleNames);

    void add(Role role);

    void update(Role role);

    Role getById(Long id);
}
