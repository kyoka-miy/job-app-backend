package com.example.securityjwtpracticespring.applications;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/application")
@RequiredArgsConstructor
@CrossOrigin(origins = "${spring.mvc.cors.allowed-origins}")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/add/{userId}")
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
    @GetMapping("/search/{userId}/{searchText}")
    public List<Application> getApplicationBySearchText (
        @PathVariable("userId") Integer userId,
        @PathVariable("searchText") String searchText
    ) {
        return applicationService.getSearchedApplications(userId, searchText);
    }
    @GetMapping("/{userId}/status")
    public List<Application> getApplicationByStatus (
        @PathVariable("userId") Integer userId,
        @RequestParam("status") Status status
    ) {
        return applicationService.getApplicationsByStatus(userId, status);
    }

    @PutMapping("/update/{applicationId}")
    public ResponseEntity<Application> updateApplication (
            @PathVariable Integer applicationId,
            @RequestBody ApplicationRequest applicationRequest
    ) {
        return ResponseEntity.ok(applicationService.updateApplication(applicationId, applicationRequest));
    }

    @DeleteMapping("/delete/{applicationId}")
    public void deleteApplication (
            @PathVariable Integer applicationId
    ) {
        applicationService.deleteApplication(applicationId);
    }
}