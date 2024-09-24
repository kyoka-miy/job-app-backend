package com.example.job_app.domain.job

interface JobRepository {
    fun insert(job: Job)
    fun fetch(jobId: String): Job?
    fun update(job: Job)
    fun delete(jobId: String)
    fun fetchByBoardId(boardId: String): List<Job>
    fun fetchByBoardIdAndStatus(boardId: String, status: Status): List<Job>
}
