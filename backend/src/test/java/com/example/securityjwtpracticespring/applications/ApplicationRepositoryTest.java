package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.Role;
import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationRepositoryTest {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() { applicationRepository.deleteAll(); }

    @Test
    void itShouldGetApplicationsBySearch() {
        // given
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
        userRepository.save(user);
        Application app1 = new Application(
                "Amama",
                "Job1",
                LocalDate.now(),
                "Taiwan",
                "comment",
                Status.RESUME_SUBMITTED,
                user
        );
        Application app2 = new Application(
                "Amama",
                "Job2",
                LocalDate.now(),
                "Taiwan",
                "comment",
                Status.RESUME_SUBMITTED,
                user
        );
        applicationRepository.save(app1);
        applicationRepository.save(app2);

        // when
        List<Application> result = applicationRepository.findBySearchText(user, "Job1");

        // then
        assertEquals(1, result.size());
    }
}