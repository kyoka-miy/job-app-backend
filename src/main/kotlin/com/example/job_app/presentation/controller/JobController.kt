package com.example.job_app.presentation.controller

import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.Remote
import com.example.job_app.domain.job.Status
import com.example.job_app.usecase.job.AddJobUseCase
import com.example.job_app.usecase.job.DeleteJobUseCase
import com.example.job_app.usecase.job.GetJobsByStatusUseCase
import com.example.job_app.usecase.job.UpdateJobUseCase
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
    private val getJobsByStatusUseCase: GetJobsByStatusUseCase
) {
    @PostMapping("/boards/{boardId}")
    fun addJob(
        @PathVariable("boardId") boardId: String,
        @RequestBody @Validated request: AddOrUpdateJobRequest
    ) {
        addJobUseCase.execute(
            boardId,
            request.jobTitle,
            request.companyName,
            request.url,
            request.location,
            request.salary,
            request.remote,
            request.description,
            request.status,
            request.appliedDate,
            request.jobBoard,
            request.note
        )
    }

    @PutMapping("/{jobId}")
    fun updateJob(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated request: AddOrUpdateJobRequest
    ) {
        updateJobUseCase.execute(
            jobId,
            request.jobTitle,
            request.companyName,
            request.url,
            request.location,
            request.salary,
            request.remote,
            request.description,
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

    @GetMapping("/boards/{boardId}")
    fun getJobsByStatus(@PathVariable("boardId") boardId: String): Map<Status, List<Job>> {
        return getJobsByStatusUseCase.execute(boardId)
    }
}

data class AddOrUpdateJobRequest (
    @field:NotBlank(message = "jobTitle cannot be blank")
    val jobTitle: String,
    @field:NotBlank(message = "companyName cannot be blank")
    val companyName: String,
    val url: String?,
    val location: String?,
    val salary: String?,
    val remote: Remote?,
    val description: String?,
    @field:NotNull(message = "status cannot be null")
    val status: Status,
    val appliedDate: LocalDate?,
    val jobBoard: String?,
    val note: String?,
)
