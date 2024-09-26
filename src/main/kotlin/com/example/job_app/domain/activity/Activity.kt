package com.example.job_app.domain.activity

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Activity(
    val activityId: String = IdGenerator.generate(),
    var name: String,
    var activityDateTime: LocalDateTime,
    var deleted: Boolean,
    val jobId: String
)
