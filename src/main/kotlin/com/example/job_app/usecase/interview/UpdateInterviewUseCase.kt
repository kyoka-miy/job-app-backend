package com.example.job_app.usecase.interview

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.interview.InterviewStage
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UpdateInterviewUseCase(
    private val interviewRepository: InterviewRepository
) {
    fun execute(interviewId: String, interviewDateTime: LocalDateTime, stage: InterviewStage, type: String, note: String?) {
        val interview = interviewRepository.fetch(interviewId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        val active = LocalDateTime.now() < interviewDateTime
        interview.interviewDateTime = interviewDateTime
        interview.stage = stage
        interview.type = type
        interview.note = note
        interview.active = active
        interviewRepository.update(interview)
    }
}
