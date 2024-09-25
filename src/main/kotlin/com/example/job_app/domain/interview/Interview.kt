package com.example.job_app.domain.interview

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Interview(
    val interviewId: String = IdGenerator.generate(),
    var interviewDateTime: LocalDateTime,
    var stage: InterviewStage,
    var type: String,
    var note: String?,
    var active: Boolean,
    val jobId: String
)

enum class InterviewStage {
    PHONE_SCREENING,
    FIRST_INTERVIEW,
    SECOND_INTERVIEW,
    THIRD_INTERVIEW,
    FINAL_INTERVIEW
}
