package com.example.job_app.usecase.assignment

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteAssignmentsUseCase(
    private val assignmentRepository: AssignmentRepository,
    private val activityRepository: ActivityRepository
) {
    fun execute(assignmentId: String) {
        val assignment = assignmentRepository.fetch(assignmentId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        activityRepository.delete(assignment.activityId)
        assignmentRepository.delete(assignmentId)
    }
}
