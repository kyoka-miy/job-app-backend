package com.example.job_app.usecase.interview

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetInterviewsUseCase(
    private val interviewRepository: InterviewRepository,
    private val jobRepository: JobRepository
) {
    fun execute(jobId: String): List<Interview> {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        return interviewRepository.fetchByJobId(jobId)
    }
}
