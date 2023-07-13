package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.Role;
import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void canPostApplications() {
        ApplicationRequest request = new ApplicationRequest(
                "Amama",
                "Job",
                LocalDate.now(),
                "Taiwan",
                "comment"
        );
        User user = new User(1, "Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
        Application application = new Application(
                "Amama",
                "Job",
                LocalDate.now(),
                "Taiwan",
                "comment",
                Status.RESUME_SUBMITTED,
                user
        );
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // when
        assertDoesNotThrow(() -> applicationService.register(1, request));

        // then
        verify(userRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).save(application);
    }
//    @Test
//    @Disabled
//    void canGetAllApplicationsOfUser() {
//        // given
//        ApplicationRequest request = new ApplicationRequest(
//                "Amama",
//                "Job",
//                LocalDate.now(),
//                "Taiwan",
//                "comment"
//        );
//
//        // when
//        applicationService.register(1, request);
//
//        // then
//        Optional<Application> app1 = applicationRepository.findById(1);
//        assertTrue(app1.isPresent());
//        assertEquals(app1.get(), new Application(
//                1,
//                "Amama",
//                "Job",
//                LocalDate.now(),
//                "Taiwan",
//                "comment",
//                Status.RESUME_SUBMITTED,
//                user
//        ));
//    }
}