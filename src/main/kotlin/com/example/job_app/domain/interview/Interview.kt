package com.example.job_app.domain.interview

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Interview(
    val interviewId: String = IdGenerator.generate(),
    var title: String,
    var interviewDateTime: LocalDateTime,
    var note: String?,
    var completed: Boolean,
    val jobId: String,
    val activityId: String
)
