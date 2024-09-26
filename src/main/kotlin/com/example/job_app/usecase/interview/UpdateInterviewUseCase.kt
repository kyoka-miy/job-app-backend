package com.example.job_app.usecase.interview

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Transactional
class UpdateInterviewUseCase(
    private val interviewRepository: InterviewRepository,
    private val activityRepository: ActivityRepository
) {
    fun execute(interviewId: String, interviewDateTime: LocalDateTime, stage: String, type: String, note: String?) {
        val interview = interviewRepository.fetch(interviewId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        val activity = activityRepository.fetch(interview.activityId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Activity not found")
        activity.name = stage
        activity.activityDateTime = interviewDateTime
        activity.deleted = false
        activityRepository.update(activity)

        val active = LocalDateTime.now() < interviewDateTime
        interview.interviewDateTime = interviewDateTime
        interview.stage = stage
        interview.type = type
        interview.note = note
        interview.active = active
        interviewRepository.update(interview)
    }
}
