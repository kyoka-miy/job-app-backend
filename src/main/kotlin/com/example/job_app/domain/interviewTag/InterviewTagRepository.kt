package com.example.job_app.domain.interviewTag

interface InterviewTagRepository {
    fun insert(interviewTag: InterviewTag)
    fun fetch(interviewId: String): List<InterviewTag>
    fun deleteAll(interviewId: String)
}
