package com.example.job_app.domain.assignment

interface AssignmentRepository {
    fun fetchByJobId(jobId: String): List<Assignment>
    fun insert(assignment: Assignment)
    fun fetch(assignmentId: String): Assignment?
    fun delete(assignmentId: String)
    fun update(assignment: Assignment)
}
