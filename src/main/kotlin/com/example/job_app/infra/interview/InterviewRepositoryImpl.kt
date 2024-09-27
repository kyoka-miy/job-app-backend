package com.example.job_app.infra.interview

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.InterviewsRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class InterviewRepositoryImpl(
    private val jooq: DSLContext
) : InterviewRepository {
    override fun insert(interview: Interview) {
        try {
            jooq.insertInto(Tables.INTERVIEWS)
                .set(Tables.INTERVIEWS.INTERVIEW_ID, interview.interviewId)
                .set(Tables.INTERVIEWS.INTERVIEW_DATETIME, interview.interviewDateTime)
                .set(Tables.INTERVIEWS.STAGE, interview.stage)
                .set(Tables.INTERVIEWS.TYPE, interview.type)
                .set(Tables.INTERVIEWS.NOTE, interview.note)
                .set(Tables.INTERVIEWS.ACTIVE, interview.active)
                .set(Tables.INTERVIEWS.JOB_ID, interview.jobId)
                .set(Tables.INTERVIEWS.ACTIVITY_ID, interview.activityId)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.Common.duplicate, "Duplicate key")
        }
    }

    override fun fetch(interviewId: String): Interview? {
        return jooq.selectFrom(Tables.INTERVIEWS)
            .where(Tables.INTERVIEWS.INTERVIEW_ID.eq(interviewId))
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun delete(interviewId: String) {
        jooq.deleteFrom(Tables.INTERVIEWS)
            .where(Tables.INTERVIEWS.INTERVIEW_ID.eq(interviewId))
            .execute()
    }

    override fun update(interview: Interview) {
        jooq.update(Tables.INTERVIEWS)
            .set(Tables.INTERVIEWS.INTERVIEW_DATETIME, interview.interviewDateTime)
            .set(Tables.INTERVIEWS.STAGE, interview.stage)
            .set(Tables.INTERVIEWS.TYPE, interview.type)
            .set(Tables.INTERVIEWS.NOTE, interview.note)
            .set(Tables.INTERVIEWS.ACTIVE, interview.active)
            .execute()
    }

    override fun fetchByJobId(jobId: String): List<Interview> {
        return jooq.selectFrom(Tables.INTERVIEWS)
            .where(Tables.INTERVIEWS.JOB_ID.eq(jobId))
            .orderBy(Tables.INTERVIEWS.INTERVIEW_DATETIME.desc())
            .mapNotNull {
                recordToEntity(it)
            }
    }

    private fun recordToEntity(record: InterviewsRecord): Interview {
        return Interview(
            interviewId = record.interviewId,
            interviewDateTime = record.interviewDatetime,
            stage = record.stage,
            type = record.type,
            note = record.note,
            active = record.active,
            jobId = record.jobId,
            activityId = record.activityId
        )
    }
}
