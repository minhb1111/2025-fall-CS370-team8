package com.subclear.config;

import com.subclear.model.User;
import com.subclear.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create demo admin account if it doesn't exist
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@subclear.com");
            admin.setFullName("Admin User");
            userRepository.save(admin);
            System.out.println("✓ Demo admin account created: username=admin, password=admin123");
        }
        
        // Create demo user account if it doesn't exist
        if (!userRepository.existsByUsername("demo")) {
            User demo = new User();
            demo.setUsername("demo");
            demo.setPassword("demo123");
            demo.setEmail("demo@subclear.com");
            demo.setFullName("Demo User");
            userRepository.save(demo);
            System.out.println("✓ Demo user account created: username=demo, password=demo123");
        }
    }
}
