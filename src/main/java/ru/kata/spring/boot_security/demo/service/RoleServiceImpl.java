package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.Set;

@Component
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getSetOfRoles(String[] roleNames) {
        return roleRepository.getSetOfRoles(roleNames);
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleRepository.add(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleRepository.update(role);

    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }
}
