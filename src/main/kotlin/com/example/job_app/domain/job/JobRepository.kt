package com.example.job_app.domain.job

import com.example.job_app.usecase.job.JobWithInterviewDto

interface JobRepository {
    fun insert(job: Job)
    fun fetch(jobId: String): Job?
    fun update(job: Job)
    fun delete(jobId: String)
    fun fetchByBoardId(boardId: String): List<Job>
    fun fetchByBoardIdWithInterview(boardId: String): List<JobWithInterviewDto>
    fun fetchByBoardIdAndStatus(boardId: String, status: Status): List<Job>
}
