package com.example.job_app.usecase.assignment

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetAssignmentsUseCase(
    private val jobRepository: JobRepository,
    private val assignmentRepository: AssignmentRepository
) {
    fun execute(jobId: String): List<Assignment> {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        return assignmentRepository.fetchByJobId(jobId)
    }
}
