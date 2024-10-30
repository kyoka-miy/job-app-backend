package com.example.job_app.domain.job

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDate
import java.time.LocalDateTime

data class Job(
    val jobId: String = IdGenerator.generate(),
    var jobTitle: String,
    var companyName: String,
    var url: String?,
    var location: String?,
    var salary: String?,
    var workStyle: WorkStyle?,
    var status: Status,
    var appliedDate: LocalDate?,
    var jobBoard: String?,
    var note: String?,
    val addedDatetime: LocalDateTime,
    val boardId: String
)

enum class WorkStyle {
    REMOTE, ON_SITE, HYBRID
}

enum class Status {
    APPLIED, INTERVIEW, OFFER, REJECTED, WISHLIST, OFFER_ACCEPTED
}
