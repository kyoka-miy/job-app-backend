package com.example.job_app.domain.offer

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Offer(
    val offerId: String = IdGenerator.generate(),
    var receivedDateTime: LocalDateTime,
    var deadlineDateTime: LocalDateTime,
    var acceptedDateTime: LocalDateTime?,
    val jobId: String,
    val activityId: String,
    val boardId: String
)
