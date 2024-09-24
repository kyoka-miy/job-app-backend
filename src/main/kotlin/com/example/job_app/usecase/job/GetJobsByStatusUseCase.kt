package com.example.job_app.usecase.job

import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import org.springframework.stereotype.Component

@Component
class GetJobsByStatusUseCase(
    private val jobRepository: JobRepository
) {
    fun execute(boardId: String, status: Status): List<Job> {
        return jobRepository.fetchByBoardIdAndStatus(boardId, status)
    }
}
