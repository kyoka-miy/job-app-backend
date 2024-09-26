package com.example.job_app.usecase.activity

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val jobRepository: JobRepository
) {
    fun execute(jobId: String, name: String, activityDateTime: LocalDateTime) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        val activity = Activity(
            name = name,
            activityDateTime = activityDateTime,
            deleted = false,
            jobId = jobId
        )
        activityRepository.insert(activity)
    }
}
