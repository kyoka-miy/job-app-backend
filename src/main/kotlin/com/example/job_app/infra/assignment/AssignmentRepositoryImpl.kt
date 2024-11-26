package com.example.job_app.infra.assignment

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.AssignmentsRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class AssignmentRepositoryImpl(
    private val jooq: DSLContext
) : AssignmentRepository {
    override fun fetchByJobId(jobId: String): List<Assignment> {
        return jooq.selectFrom(Tables.ASSIGNMENTS)
            .where(Tables.ASSIGNMENTS.JOB_ID.eq(jobId))
            .orderBy(Tables.ASSIGNMENTS.DEADLINE_DATETIME)
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    private fun recordToEntity(record: AssignmentsRecord): Assignment {
        return Assignment(
            assignmentId = record.assignmentId,
            title = record.title,
            deadlineDatetime = record.deadlineDatetime,
            note = record.note,
            completed = record.completed,
            jobId = record.jobId
        )
    }
}
