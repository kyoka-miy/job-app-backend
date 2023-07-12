package com.example.securityjwtpracticespring.applications;

import com.example.securityjwtpracticespring.user.Role;
import com.example.securityjwtpracticespring.user.User;
import com.example.securityjwtpracticespring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    public void register(Integer userId, ApplicationRequest request) {
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
                .status(Status.RESUME_SUBMITTED)
                .user(user.get())
                .build();
        applicationRepository.save(application);
    }

    public List<Application> getApplications(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return applicationRepository.findByUserId(user.get().getUserId());
    }
}
