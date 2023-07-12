package com.example.securityjwtpracticespring.config;

import com.example.securityjwtpracticespring.applications.Application;
import com.example.securityjwtpracticespring.applications.ApplicationRepository;
import com.example.securityjwtpracticespring.applications.Status;
import com.example.securityjwtpracticespring.user.Role;
import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SampleDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    public SampleDataInitializer(UserRepository userRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }
    @Override
    public void run(String... args) {
        User user1 = new User(1, "John", "Doe", "john@example.com", "$2a$10$G5e8Vy....", Role.USER);
        userRepository.save(user1);

        User user2 = new User(2, "Jane", "Smith", "jane@example.com", "$2a$10$G5e8Vy....", Role.USER);
        userRepository.save(user2);

        Application application1 = new Application(1, "Amapon", "Software Engineer", LocalDate.now(), "Uganda", "commentcomment", Status.RESUME_SUBMITTED, user1);
        applicationRepository.save(application1);
        Application application2 = new Application(2, "Amapon", "Software Engineer", LocalDate.now(), "Uganda", "commentcomment", Status.RESUME_SUBMITTED, user2);
        applicationRepository.save(application2);
    }
}

