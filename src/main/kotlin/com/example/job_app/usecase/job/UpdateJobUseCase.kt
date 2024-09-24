package com.example.job_app.usecase.job

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Remote
import com.example.job_app.domain.job.Status
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UpdateJobUseCase(
    private val jobRepository: JobRepository
) {
    fun execute(
        jobId: String,
        jobTitle: String,
        companyName: String,
        url: String?,
        location: String?,
        salary: String?,
        remote: Remote?,
        description: String?,
        status: Status,
        appliedDate: LocalDate?,
        jobBoard: String?,
        note: String?
    ) {
        val job = jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        job.jobTitle = jobTitle
        job.companyName = companyName
        job.url = url
        job.location = location
        job.salary = salary
        job.remote = remote
        job.description = description
        job.status = status
        job.appliedDate = appliedDate
        job.jobBoard = jobBoard
        job.note = note
        jobRepository.update(job)
    }
}
