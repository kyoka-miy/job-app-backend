package com.example.job_app.usecase.interview

import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class InactivateInterviewUseCase(
    private val interviewRepository: InterviewRepository
) {
    fun execute(interviewId: String) {
        val interview = interviewRepository.fetch(interviewId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        interview.active = false
        interviewRepository.update(interview)
    }
}
