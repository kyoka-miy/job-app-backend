package com.example.job_app.presentation.controller

import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.Status
import com.example.job_app.domain.job.WorkStyle
import com.example.job_app.presentation.shared.RequestHeaderContext
import com.example.job_app.usecase.job.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/jobs")
class JobController(
    private val addJobUseCase: AddJobUseCase,
    private val updateJobUseCase: UpdateJobUseCase,
    private val deleteJobUseCase: DeleteJobUseCase,
    private val getJobsByStatusUseCase: GetJobsByStatusUseCase,
    private val getJobsWithInterviewsUseCase: GetJobsWithInterviewsUseCase,
    private val getJobsWithAssignmentsUseCase: GetJobsWithAssignmentsUseCase,
    private val requestHeaderContext: RequestHeaderContext
) {
    @PostMapping
    fun addJob(
        @RequestBody @Validated
        request: AddOrUpdateJobRequest
    ) {
        addJobUseCase.execute(
            requestHeaderContext.getBoardId(),
            request.jobTitle,
            request.companyName,
            request.url,
            request.location,
            request.placeId,
            request.salary,
            request.workStyle,
            request.jobBoard,
            request.note,
            request.status,
            request.appliedDate
        )
    }

    @PutMapping("/{jobId}")
    fun updateJob(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated
        request: AddOrUpdateJobRequest
    ) {
        updateJobUseCase.execute(
            jobId,
            request.jobTitle,
            request.companyName,
            request.url,
            request.location,
            request.placeId,
            request.salary,
            request.workStyle,
            request.status,
            request.appliedDate,
            request.jobBoard,
            request.note
        )
    }

    @DeleteMapping("/{jobId}")
    fun deleteJob(
        @PathVariable("jobId") jobId: String
    ) {
        deleteJobUseCase.execute(jobId)
    }

    @GetMapping
    fun getJobsByStatus(
        @RequestParam status: Status
    ): List<Job> {
        return getJobsByStatusUseCase.execute(
            requestHeaderContext.getBoardId(),
            status
        )
    }

    @GetMapping("/interviews")
    fun getJobsWithInterviews(): List<JobWithInterviewDto> {
        return getJobsWithInterviewsUseCase.execute(
            requestHeaderContext.getBoardId()
        )
    }

    @GetMapping("/assignments")
    fun getJobsWithAssignments(): List<JobWithAssignmentDto> {
        return getJobsWithAssignmentsUseCase.execute(
            requestHeaderContext.getBoardId()
        )
    }
}

data class AddOrUpdateJobRequest(
    @field:NotBlank(message = "companyName cannot be blank")
    val companyName: String,
    @field:NotBlank(message = "jobTitle cannot be blank")
    val jobTitle: String,
    val appliedDate: LocalDate?,
    val url: String?,
    val location: String?,
    val placeId: String?,
    @field:NotNull(message = "status cannot be null")
    val status: Status,
    val workStyle: WorkStyle?,
    val salary: String?,
    val jobBoard: String?,
    val note: String?
)
