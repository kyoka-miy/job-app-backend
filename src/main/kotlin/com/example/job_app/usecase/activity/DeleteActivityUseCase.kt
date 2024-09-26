package com.example.job_app.usecase.activity

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteActivityUseCase(
    private val activityRepository: ActivityRepository
) {
    fun execute(activityId: String) {
        activityRepository.fetch(activityId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Activity not found")
        activityRepository.delete(activityId)
    }
}
