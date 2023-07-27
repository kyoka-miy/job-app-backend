package com.example.securityjwtpracticespring.applications;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/{userId}")
    public ResponseEntity<Application> addApplication (
        @RequestBody ApplicationRequest applicationRequest,
        @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(applicationService.register(userId, applicationRequest));
    }
    @GetMapping("/{userId}")
    public List<Application> getApplication (
        @PathVariable("userId") Integer userId
    ) {
        return applicationService.getApplications(userId);
    }
    @GetMapping("/{userId}/{searchText}")
    public List<Application> getApplication (
        @PathVariable("userId") Integer userId,
        @PathVariable("searchText") String searchText
    ) {
        return applicationService.getSearchedApplications(userId, searchText);
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<Application> updateApplication (
            @PathVariable Integer applicationId,
            @RequestBody ApplicationRequest applicationRequest
    ) {
        return ResponseEntity.ok(applicationService.updateApplication(applicationId, applicationRequest));
    }

    @DeleteMapping("/{applicationId}")
    public void deleteApplication (
            @PathVariable Integer applicationId
    ) {
        applicationService.deleteApplication(applicationId);
    }
}