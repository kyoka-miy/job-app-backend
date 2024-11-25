package com.example.job_app.usecase.interview

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.interviewTag.InterviewTagRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteInterviewUseCase(
    private val interviewRepository: InterviewRepository,
    private val activityRepository: ActivityRepository,
    private val interviewTagRepository: InterviewTagRepository
) {
    fun execute(interviewId: String) {
        val interview = interviewRepository.fetch(interviewId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        activityRepository.delete(interview.activityId)
        interviewTagRepository.deleteAll(interviewId)
        interviewRepository.delete(interviewId)
    }
}
