package com.example.job_app.domain.interview

interface InterviewRepository {
    fun insert(interview: Interview)
    fun fetch(interviewId: String): Interview?
    fun delete(interviewId: String)
    fun update(interview: Interview)
    fun fetchByJobId(jobId: String): List<Interview>
}
