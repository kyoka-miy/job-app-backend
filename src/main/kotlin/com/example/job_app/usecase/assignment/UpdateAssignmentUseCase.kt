package com.example.job_app.usecase.assignment

import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UpdateAssignmentUseCase(
    private val assignmentRepository: AssignmentRepository,
    private val activityRepository: ActivityRepository
) {
    fun execute(
        assignmentId: String,
        title: String,
        deadlineDatetime: LocalDateTime,
        note: String?,
        completed: Boolean
    ) {
        val assignment = assignmentRepository.fetch(assignmentId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Interview not found")
        val activity = activityRepository.fetch(assignment.activityId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Activity not found")
        activity.name = title
        activity.activityDateTime = deadlineDatetime
        activity.deleted = false
        activityRepository.update(activity)

        assignment.title = title
        assignment.deadlineDatetime = deadlineDatetime
        assignment.note = note
        assignment.completed = completed
        assignmentRepository.update(assignment)
    }
}
