
package com.priya.actifyusermanagement;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.priya.actifyusermanagement.entity.Role;
import com.priya.actifyusermanagement.entity.User;
import com.priya.actifyusermanagement.repository.RoleRepository;
import com.priya.actifyusermanagement.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ADMIN");
                        return roleRepository.save(role);
                    });

            roleRepository.findByName("MANAGER").orElseGet(() -> {
                Role role = new Role();
                role.setName("MANAGER");
                return roleRepository.save(role);
            });

            roleRepository.findByName("USER").orElseGet(() -> {
                Role role = new Role();
                role.setName("USER");
                return roleRepository.save(role);
            });

            if (!userRepository.existsByEmail("admin@actify.com")) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@actify.com");
                admin.setPassword(passwordEncoder.encode("Admin@123"));

                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);
            }
        };
    }
}
