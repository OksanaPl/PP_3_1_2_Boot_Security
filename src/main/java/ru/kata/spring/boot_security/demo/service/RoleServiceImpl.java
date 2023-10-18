package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getSetOfRoles(List<Long> idList) {
        Set<Role> roleSet = new HashSet<>();
        for (Long id : idList) {
            roleSet.add(getRoleById(id));
        }

        return roleSet;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleRepository.save(role);
    }

}
