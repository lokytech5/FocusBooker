package com.lokytech.FocusBooker.configuration;

import com.lokytech.FocusBooker.entity.Roles;
import com.lokytech.FocusBooker.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            insertRoleIfNotFound("USER");
            insertRoleIfNotFound("ADMIN");
        };
    }

    private void insertRoleIfNotFound(String roleName) {
        Optional<Roles> optionalRole = roleRepository.findByName(roleName);
        if (optionalRole.isEmpty()) {
            Roles newRole = new Roles();
            newRole.setName(roleName);
            roleRepository.save(newRole);
        }
    }

}
