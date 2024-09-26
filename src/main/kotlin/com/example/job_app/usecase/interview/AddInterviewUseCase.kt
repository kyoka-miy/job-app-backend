package com.example.job_app.usecase.interview

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Transactional
class AddInterviewUseCase(
    private val interviewRepository: InterviewRepository,
    private val jobRepository: JobRepository,
    private val activityRepository: ActivityRepository
) {
    fun execute(jobId: String, interviewDateTime: LocalDateTime, stage: String, type: String, note: String?) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        val activity = Activity(
            name = stage,
            activityDateTime = interviewDateTime,
            deleted = false,
            jobId = jobId
        )
        activityRepository.insert(activity)
        val active = LocalDateTime.now() < interviewDateTime
        val interview = Interview(
            interviewDateTime = interviewDateTime,
            stage = stage,
            type = type,
            note = note,
            active = active,
            jobId = jobId,
            activityId = activity.activityId
        )
        interviewRepository.insert(interview)
    }
}
