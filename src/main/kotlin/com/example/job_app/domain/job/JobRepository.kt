package com.example.job_app.domain.job

import com.example.job_app.usecase.job.JobWithAssignmentDto
import com.example.job_app.usecase.job.JobWithInterviewDto

interface JobRepository {
    fun insert(job: Job)
    fun fetch(jobId: String): Job?
    fun update(job: Job)
    fun delete(jobId: String)
    fun fetchByBoardId(boardId: String): List<Job>
    fun fetchByBoardIdWithInterview(boardId: String): List<JobWithInterviewDto>
    fun fetchByBoardIdWithAssignment(boardId: String): List<JobWithAssignmentDto>
    fun fetchByBoardIdAndStatusAndText(boardId: String, status: Status, text: String?): List<Job>
}
