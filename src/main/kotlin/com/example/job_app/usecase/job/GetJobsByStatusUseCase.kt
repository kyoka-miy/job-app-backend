package com.example.job_app.usecase.job

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetJobsByStatusUseCase(
    private val jobRepository: JobRepository,
    private val boardRepository: BoardRepository
) {
    fun execute(boardId: String): Map<Status, List<Job>> {
        boardRepository.fetch(boardId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Board not found")
        val jobs = jobRepository.fetchByBoardId(boardId)
        return jobs.groupBy { it.status }
    }
}
