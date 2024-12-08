package com.example.job_app.presentation.controller

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.usecase.assignment.AddAssignmentUseCase
import com.example.job_app.usecase.assignment.DeleteAssignmentsUseCase
import com.example.job_app.usecase.assignment.GetAssignmentsUseCase
import com.example.job_app.usecase.assignment.UpdateAssignmentUseCase
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/assignments")
class AssignmentController(
    private val getAssignmentsUseCase: GetAssignmentsUseCase,
    private val addAssignmentUseCase: AddAssignmentUseCase,
    private val deleteAssignmentUseCase: DeleteAssignmentsUseCase,
    private val updateAssignmentUseCase: UpdateAssignmentUseCase
) {
    @GetMapping("/jobs/{jobId}")
    fun getAssignments(
        @PathVariable("jobId") jobId: String
    ): List<Assignment> {
        return getAssignmentsUseCase.execute(jobId)
    }

    @PostMapping("/jobs/{jobId}")
    fun addAssignment(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated
        request: AddOrUpdateAssignmentRequest
    ) {
        addAssignmentUseCase.execute(
            jobId,
            request.title,
            request.deadlineDatetime,
            request.note,
            request.completed
        )
    }

    @DeleteMapping("/{assignmentId}")
    fun deleteAssignment(@PathVariable("assignmentId") assignmentId: String) {
        deleteAssignmentUseCase.execute(assignmentId)
    }

    @PutMapping("/{assignmentId}")
    fun updateAssignment(
        @PathVariable("assignmentId") assignmentId: String,
        @RequestBody @Validated
        request: AddOrUpdateAssignmentRequest
    ) {
        updateAssignmentUseCase.execute(
            assignmentId,
            request.title,
            request.deadlineDatetime,
            request.note,
            request.completed
        )
    }
}

data class AddOrUpdateAssignmentRequest(
    val title: String,
    val deadlineDatetime: LocalDateTime,
    val note: String?,
    val completed: Boolean
)
