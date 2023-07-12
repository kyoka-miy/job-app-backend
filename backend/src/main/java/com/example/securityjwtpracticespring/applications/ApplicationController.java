package com.example.securityjwtpracticespring.applications;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/{userId}")
    public void addApplication (
        @RequestBody ApplicationRequest applicationRequest,
        @PathVariable Integer userId
    ) {
        applicationService.register(userId, applicationRequest);
    }
    @GetMapping("/{userId}")
    public List<Application> getApplication (
        @PathVariable Integer userId
    ) {
        return applicationService.getApplications(userId);
    }
}
