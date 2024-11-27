package com.art.service.impl;

import com.art.domain.USER_ROLE;
import com.art.modal.User;
import com.art.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "ranirangari1999@gmail.com";

        if(userRepository.findByEmail(adminUsername)==null){
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("ranirangari"));
            adminUser.setFullName("RANI");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(USER_ROLE.ROLE_ADMIN);

            User admin = userRepository.save(adminUser);
        }
    }
}
