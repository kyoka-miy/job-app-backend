package com.example.job_app.infra.assignment

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.domain.assignment.AssignmentRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
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

    override fun insert(assignment: Assignment) {
        try {
            jooq.insertInto(Tables.ASSIGNMENTS)
                .set(Tables.ASSIGNMENTS.ASSIGNMENT_ID, assignment.assignmentId)
                .set(Tables.ASSIGNMENTS.TITLE, assignment.title)
                .set(Tables.ASSIGNMENTS.DEADLINE_DATETIME, assignment.deadlineDatetime)
                .set(Tables.ASSIGNMENTS.NOTE, assignment.note)
                .set(Tables.ASSIGNMENTS.COMPLETED, assignment.completed)
                .set(Tables.ASSIGNMENTS.JOB_ID, assignment.jobId)
                .set(Tables.ASSIGNMENTS.ACTIVITY_ID, assignment.activityId)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.Common.duplicate, "Duplicate key")
        }
    }

    override fun fetch(assignmentId: String): Assignment? {
        return jooq.selectFrom(Tables.ASSIGNMENTS)
            .where(Tables.ASSIGNMENTS.ASSIGNMENT_ID.eq(assignmentId))
            .fetchOne()
            ?.let { recordToEntity(it) }
    }

    override fun delete(assignmentId: String) {
        jooq.deleteFrom(Tables.ASSIGNMENTS)
            .where(Tables.ASSIGNMENTS.ASSIGNMENT_ID.eq(assignmentId))
            .execute()
    }

    override fun update(assignment: Assignment) {
        jooq.update(Tables.ASSIGNMENTS)
            .set(Tables.ASSIGNMENTS.TITLE, assignment.title)
            .set(Tables.ASSIGNMENTS.DEADLINE_DATETIME, assignment.deadlineDatetime)
            .set(Tables.ASSIGNMENTS.NOTE, assignment.note)
            .set(Tables.ASSIGNMENTS.COMPLETED, assignment.completed)
            .where(Tables.ASSIGNMENTS.ASSIGNMENT_ID.eq(assignment.assignmentId))
            .execute()
    }

    private fun recordToEntity(record: AssignmentsRecord): Assignment {
        return Assignment(
            assignmentId = record.assignmentId,
            title = record.title,
            deadlineDatetime = record.deadlineDatetime,
            note = record.note,
            completed = record.completed,
            jobId = record.jobId,
            activityId = record.activityId
        )
    }
}
