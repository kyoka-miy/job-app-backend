package com.example.job_app.usecase.activity

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UpdateActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    fun execute(activityId: String, name: String, activityDateTime: LocalDateTime) {
        val activity = activityRepository.fetch(activityId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Activity not found")
        activity.name = name
        activity.activityDateTime = activityDateTime
        activityRepository.update(activity)
    }
}
