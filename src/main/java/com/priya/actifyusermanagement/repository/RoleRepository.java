package com.priya.actifyusermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priya.actifyusermanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}