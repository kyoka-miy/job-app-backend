package com.example.job_app.infra.interviewTag

import com.example.job_app.domain.interviewTag.InterviewTag
import com.example.job_app.domain.interviewTag.InterviewTagRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class InterviewTagRepositoryImpl(
    private val jooq: DSLContext
) : InterviewTagRepository {
    override fun insert(interviewTag: InterviewTag) {
        try {
            jooq.insertInto(Tables.INTERVIEW_TAGS)
                .set(Tables.INTERVIEW_TAGS.INTERVIEW_ID, interviewTag.interviewId)
                .set(Tables.INTERVIEW_TAGS.NAME, interviewTag.name.name)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.Common.duplicate, "Duplicate key")
        }
    }
}
