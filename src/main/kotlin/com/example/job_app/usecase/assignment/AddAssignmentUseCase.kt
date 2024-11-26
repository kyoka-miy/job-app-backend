package com.example.job_app.usecase.assignment

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddAssignmentUseCase(
    private val jobRepository: JobRepository,
    private val activityRepository: ActivityRepository,
    private val assignmentRepository: AssignmentRepository
) {
    fun execute(
        jobId: String,
        title: String,
        deadlineDatetime: LocalDateTime,
        note: String?,
        completed: Boolean
    ) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        val activity = Activity(
            name = title,
            activityDateTime = deadlineDatetime,
            deleted = false,
            jobId = jobId
        )
        activityRepository.insert(activity)

        val assignment = Assignment(
            title = title,
            deadlineDatetime = deadlineDatetime,
            note = note,
            completed = completed,
            jobId = jobId,
            activityId = activity.activityId
        )
        assignmentRepository.insert(assignment)
    }
}
