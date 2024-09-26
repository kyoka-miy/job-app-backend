package com.example.job_app.usecase.activity

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetActivitiesUseCase(
    private val activityRepository: ActivityRepository,
    private val jobRepository: JobRepository
) {
    fun execute(jobId: String): List<Activity> {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        return activityRepository.fetchByJobId(jobId)
    }
}
