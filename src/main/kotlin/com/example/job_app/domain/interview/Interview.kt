package com.example.job_app.domain.interview

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Interview(
    val interviewId: String = IdGenerator.generate(),
    var interviewDateTime: LocalDateTime,
    var stage: String,
    var type: String,
    var note: String?,
    var active: Boolean,
    val jobId: String,
    val activityId: String
)
