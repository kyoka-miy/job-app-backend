package com.example.job_app.presentation.controller

import com.example.job_app.domain.job_list.JobList
import com.example.job_app.usecase.job_list.GetJobListsUseCase
import com.example.job_app.usecase.job_list.UpdateJobListUseCase
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/job-lists")
class JobListController(
    private val getJobListsUseCase: GetJobListsUseCase,
    private val updateJobListUseCase: UpdateJobListUseCase
) {
    @GetMapping
    fun getJobLists(): List<JobList> {
        return getJobListsUseCase.execute()
    }

    @PutMapping("/{jobListId}")
    fun updateJobList(
        @PathVariable("jobListId") jobListId: String,
        @RequestBody @Validated
        request: UpdateJobListRequest
    ) {
        updateJobListUseCase.execute(jobListId, request.active)
    }
}

data class UpdateJobListRequest(
    @field:NotNull
    val active: Boolean
)
