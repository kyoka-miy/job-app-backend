package com.example.job_app.domain.job_list

interface JobListRepository {
    fun fetch(): List<JobList>
    fun fetchById(jobListId: String): JobList?
    fun update(jobList: JobList)
}
