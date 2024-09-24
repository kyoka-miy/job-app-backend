package com.example.job_app.domain.job

import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Job(
    val jobId: String = IdGenerator.generate(),
    var jobTitle: String,
    var companyName: String,
    var url: String?,
    var location: String?,
    var salary: String?,
    var remote: Remote?,
    var description: String?,
    var status: Status,
    var appliedDatetime: LocalDateTime?,
    var jobBoard: String?,
    var note: String?,
    val addedDatetime: LocalDateTime,
    val boardId: String
)

enum class Remote {
    REMOTE, ON_SITE, HYBRID
}

enum class Status {
    APPLIED, INTERVIEW, OFFER, REJECTED, WISH_LIST, OFFER_ACCEPTED
}
