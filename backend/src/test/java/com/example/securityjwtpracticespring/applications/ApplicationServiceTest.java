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
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
                Status.RESUME_SUBMITTED,
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

    @Test
    void canGetApplications() {
        ApplicationRequest request = new ApplicationRequest(
                "Amama",
                "Job",
                LocalDate.now(),
                "Taiwan",
                Status.RESUME_SUBMITTED,
                "comment"
        );
        User user = new User(1, "Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(applicationRepository.findByUserId(1)).thenReturn(List.of(app1, app2));

        // when
        List<Application> result = applicationService.getApplications(1);

        // then
        verify(userRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).findByUserId(1);
        assertEquals(2, result.size());
    }

    @Test
    void canDeleteApplication() {
        ApplicationRequest request = new ApplicationRequest(
                "Amama",
                "Job",
                LocalDate.now(),
                "Taiwan",
                Status.RESUME_SUBMITTED,
                "comment"
        );
        User user = new User(1, "Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
        Application app1 = new Application(
                "Amama",
                "Job1",
                LocalDate.now(),
                "Taiwan",
                "comment",
                Status.RESUME_SUBMITTED,
                user
        );
        when(applicationRepository.findById(1)).thenReturn(Optional.of(app1));

        // when
        assertDoesNotThrow(() -> applicationService.deleteApplication(1));

        // then
        verify(applicationRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).delete(app1);
    }

    @Test
    void canPutApplication() {
        ApplicationRequest request = new ApplicationRequest(
                "Amama",
                "Job",
                LocalDate.now(),
                "Taiwan",
                Status.RESUME_SUBMITTED,
                "comment"
        );
        User user = new User(1, "Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
                "Job",
                LocalDate.now(),
                "Taiwan",
                "comment",
                Status.RESUME_SUBMITTED,
                user
        );
        when(applicationRepository.findById(1)).thenReturn(Optional.of(app1));

        // when
        assertDoesNotThrow(() -> applicationService.updateApplication(1, request));

        // then
        verify(applicationRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).save(app2);
    }
    @Test
    void willThrowWhenUserIsNotFound() {
        //when
        //then
        assertThatThrownBy(() -> applicationService.getApplications(1))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User not found");
        verify(applicationRepository, never()).save(any());
    }
}