package com.example.job_app.usecase.interview

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.interviewTag.InterviewTag
import com.example.job_app.domain.interviewTag.InterviewTagRepository
import com.example.job_app.domain.interviewTag.TagName
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Transactional
class AddInterviewUseCase(
    private val interviewRepository: InterviewRepository,
    private val jobRepository: JobRepository,
    private val activityRepository: ActivityRepository,
    private val interviewtTagRepository: InterviewTagRepository
) {
    fun execute(jobId: String, title: String, tags: List<String>?, interviewDateTime: LocalDateTime, note: String?, completed: Boolean) {
        jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        val activity = Activity(
            name = title,
            activityDateTime = interviewDateTime,
            deleted = false,
            jobId = jobId
        )
        activityRepository.insert(activity)

        val interview = Interview(
            title = title,
            interviewDateTime = interviewDateTime,
            note = note,
            completed = completed,
            jobId = jobId,
            activityId = activity.activityId
        )
        interviewRepository.insert(interview)

        tags?.forEach {
            val tag = InterviewTag(
                interview.interviewId,
                TagName.valueOf(it)
            )
            interviewtTagRepository.insert(tag)
        }
    }
}
