
package com.priya.actifyusermanagement.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.priya.actifyusermanagement.entity.Role;
import com.priya.actifyusermanagement.entity.User;
import com.priya.actifyusermanagement.repository.RoleRepository;
import com.priya.actifyusermanagement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create User
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String roleName = "USER";

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            roleName = user.getRoles().iterator().next().getName();
        }

        final String selectedRole = roleName.toUpperCase();

        Role role = roleRepository.findByName(selectedRole)
                .orElseThrow(() ->
                    new RuntimeException("Role not found: " + selectedRole));

        user.setRoles(Set.of(role));

        return userRepository.save(user);
    }
    // Get User By ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));
    }

    // Get User By Email
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        User user = getUserById(id);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());

        return userRepository.save(user);
    }

    // Delete User
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }

    // Get All Users
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
