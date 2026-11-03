package com.priya.actifyusermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priya.actifyusermanagement.entity.Role;
import com.priya.actifyusermanagement.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Role not found"));
    }
}
