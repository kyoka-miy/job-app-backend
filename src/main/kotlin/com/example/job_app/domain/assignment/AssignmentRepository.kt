package com.example.job_app.domain.assignment

interface AssignmentRepository {
    fun fetchByJobId(jobId: String): List<Assignment>
}
