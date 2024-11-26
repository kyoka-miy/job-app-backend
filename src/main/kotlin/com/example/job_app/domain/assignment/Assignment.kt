package com.example.job_app.domain.assignment

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Assignment(
    val assignmentId: String = IdGenerator.generate(),
    val title: String,
    val deadlineDatetime: LocalDateTime,
    val note: String?,
    val completed: Boolean,
    val jobId: String
)
