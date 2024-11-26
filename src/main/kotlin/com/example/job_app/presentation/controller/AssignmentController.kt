package com.example.job_app.presentation.controller

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.usecase.assignment.GetAssignmentsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/assignments")
class AssignmentController(
    private val getAssignmentsUseCase: GetAssignmentsUseCase
) {
    @GetMapping("/jobs/{jobId}")
    fun getAssignments(
        @PathVariable("jobId") jobId: String
    ): List<Assignment> {
        return getAssignmentsUseCase.execute(jobId)
    }
}
