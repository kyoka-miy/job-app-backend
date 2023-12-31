package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.Role;
import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.securityjwtpracticespring.applications.Status.FIRST_INTERVIEW;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {
    private ApplicationService applicationService;
    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        applicationService = new ApplicationService(applicationRepository, userRepository);
    }

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
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
        when(applicationRepository.findByUser(user)).thenReturn(List.of(app1, app2));

        // when
        List<Application> result = applicationService.getApplications(1);

        // then
        verify(userRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).findByUser(user);
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
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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

    @Test
    void canGetApplicationsBySearchText() {
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
        // Mock(テスト対象外のもの)
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(applicationRepository.findBySearchText(user, "Amama")).thenReturn(List.of(app1, app2));

        // when
        List<Application> result = applicationService.getSearchedApplications(1, "Amama");

        // then
        verify(userRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).findBySearchText(user, "Amama");
        assertEquals(2, result.size());
    }

    @Test
    void canGetApplicationsByStatus() {
        User user = new User("Firstname", "Lastname", "mail@mail.com", "password", Role.USER);
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
                FIRST_INTERVIEW,
                user
        );
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(applicationRepository.findByStatus(user, FIRST_INTERVIEW)).thenReturn(List.of(app2));

        // when
        List<Application> result = applicationService.getApplicationsByStatus(1, FIRST_INTERVIEW);

        // then
        verify(userRepository, times(1)).findById(1);
        verify(applicationRepository, times(1)).findByStatus(user, FIRST_INTERVIEW);
        assertEquals(1, result.size());
    }
}