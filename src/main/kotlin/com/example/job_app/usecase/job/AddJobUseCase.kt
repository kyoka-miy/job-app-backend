package com.example.job_app.usecase.job

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import com.example.job_app.domain.job.WorkStyle
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class AddJobUseCase(
    private val jobRepository: JobRepository,
    private val boardRepository: BoardRepository
) {
    fun execute(
        boardId: String?,
        jobTitle: String,
        companyName: String,
        url: String?,
        location: String?,
        salary: String?,
        workStyle: WorkStyle?,
        jobBoard: String?,
        note: String?,
        status: Status,
        appliedDate: LocalDate?
    ) {
        if (boardId == null || boardRepository.fetch(boardId) == null) {
            throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "BoardId not found")
        }
        val job = Job(
            boardId = boardId,
            jobTitle = jobTitle,
            companyName = companyName,
            url = url,
            location = location,
            salary = salary,
            workStyle = workStyle,
            status = status,
            appliedDate = appliedDate,
            jobBoard = jobBoard,
            note = note,
            addedDatetime = LocalDateTime.now()
        )
        jobRepository.insert(job)
    }
}
