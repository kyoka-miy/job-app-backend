package com.example.job_app.usecase.job

import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteJobUseCase(
    private val jobRepository: JobRepository
) {
    fun execute(jobId: String) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        jobRepository.delete(jobId)
    }
}
