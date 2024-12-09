package com.example.job_app.usecase.job

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interviewTag.TagName
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetJobsWithInterviewsUseCase(
    private val jobRepository: JobRepository,
    private val boardRepository: BoardRepository
) {
    fun execute(boardId: String?): List<JobWithInterviewDto> {
        if (boardId == null || boardRepository.fetch(boardId) == null) {
            throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Board id not found")
        }
        return jobRepository.fetchByBoardIdWithInterview(boardId)
    }
}

data class JobWithInterviewDto(
    val job: Job,
    val interview: Interview,
    val tags: List<TagName>
)
