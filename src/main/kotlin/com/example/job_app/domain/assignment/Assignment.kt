package com.example.job_app.domain.assignment

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Assignment(
    val assignmentId: String = IdGenerator.generate(),
    var title: String,
    var deadlineDatetime: LocalDateTime,
    var note: String?,
    var completed: Boolean,
    val jobId: String,
    val activityId: String
)
