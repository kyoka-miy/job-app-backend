package com.example.job_app.usecase.job_list

import com.example.job_app.domain.job_list.JobList
import com.example.job_app.domain.job_list.JobListRepository
import org.springframework.stereotype.Component

@Component
class GetJobListsUseCase(
    private val jobListRepository: JobListRepository
) {
    fun execute(): List<JobList> {
        return jobListRepository.fetch()
    }
}
