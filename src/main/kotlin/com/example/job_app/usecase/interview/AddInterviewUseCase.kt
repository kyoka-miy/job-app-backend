package com.example.job_app.usecase.interview

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.interview.InterviewStage
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddInterviewUseCase(
    private val interviewRepository: InterviewRepository,
    private val jobRepository: JobRepository
) {
    fun execute(jobId: String, interviewDateTime: LocalDateTime, stage: InterviewStage, type: String, note: String?) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        val active = LocalDateTime.now() < interviewDateTime
        val interview = Interview(
            interviewDateTime = interviewDateTime,
            stage = stage,
            type = type,
            note = note,
            active = active,
            jobId = jobId
        )
        interviewRepository.insert(interview)
    }
}
