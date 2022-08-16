package com.example.sample;

import com.example.sample.models.ERole;
import com.example.sample.models.Role;
import com.example.sample.repository.RoleRepository;
import com.example.sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Arrays;
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final RoleRepository roleRepository;
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(ERole.ROLE_ADMIN));
        roleRepository.save(new Role(ERole.ROLE_MODERATOR));
        roleRepository.save(new Role(ERole.ROLE_USER));
    }
}
