package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.annotations.Tables;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public Application register(Integer userId, ApplicationRequest request) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        var application = Application.builder()
                .companyName(request.getCompanyName())
                .jobTitle(request.getJobTitle())
                .date(request.getDate())
                .country(request.getCountry())
                .comment(request.getComment())
                .status(request.getStatus())
                .user(user.get())
                .build();
        return applicationRepository.save(application);
    }

    public List<Application> getApplications(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return applicationRepository.findByUser(user);
    }

    public Application updateApplication(Integer applicationId, ApplicationRequest request) {
        Application selected = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        selected.setCompanyName(request.getCompanyName());
        selected.setJobTitle(request.getJobTitle());
        selected.setDate(request.getDate());
        selected.setCountry(request.getCountry());
        selected.setComment(request.getComment());
        selected.setStatus(request.getStatus());
        return applicationRepository.save(selected);
    }

    public void deleteApplication(Integer applicationId) {
        Application selected = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        applicationRepository.delete(selected);
    }

    public List<Application> getSearchedApplications(Integer userId, String searchText) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return applicationRepository.findBySearchText(user, searchText);
    }
}
