package com.example.job_app.usecase.job_list

import com.example.job_app.domain.job_list.JobListRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class UpdateJobListUseCase(
    private val jobListRepository: JobListRepository
) {
    fun execute(jobListId: String, active: Boolean) {
        val jobList = jobListRepository.fetchById(jobListId)
            ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "JobList not found")
        jobList.active = active
        jobListRepository.update(jobList)
    }
}
