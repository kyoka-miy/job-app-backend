package com.example.job_app.infra.Job

import com.example.job_app.domain.assignment.Assignment
import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interviewTag.TagName
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import com.example.job_app.domain.job.WorkStyle
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.JobsRecord
import com.example.job_app.usecase.job.JobWithAssignmentDto
import com.example.job_app.usecase.job.JobWithInterviewDto
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Component

@Component
class JobRepositoryImpl(
    private val jooq: DSLContext
) : JobRepository {
    override fun insert(job: Job) {
        try {
            jooq.insertInto(Tables.JOBS)
                .set(Tables.JOBS.JOB_ID, job.jobId)
                .set(Tables.JOBS.JOB_TITLE, job.jobTitle)
                .set(Tables.JOBS.COMPANY_NAME, job.companyName)
                .set(Tables.JOBS.URL, job.url)
                .set(Tables.JOBS.LOCATION, job.location)
                .set(Tables.JOBS.PLACE_ID, job.placeId)
                .set(Tables.JOBS.SALARY, job.salary)
                .set(Tables.JOBS.WORK_STYLE, job.workStyle?.name)
                .set(Tables.JOBS.STATUS, job.status.name)
                .set(Tables.JOBS.APPLIED_DATE, job.appliedDate)
                .set(Tables.JOBS.JOB_BOARD, job.jobBoard)
                .set(Tables.JOBS.NOTE, job.note)
                .set(Tables.JOBS.ADDED_DATETIME, job.addedDatetime)
                .set(Tables.JOBS.BOARD_ID, job.boardId)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.Common.duplicate, "Duplicated key")
        }
    }

    override fun fetch(jobId: String): Job? {
        return jooq.selectFrom(Tables.JOBS)
            .where(Tables.JOBS.JOB_ID.eq(jobId))
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun update(job: Job) {
        jooq.update(Tables.JOBS)
            .set(Tables.JOBS.JOB_ID, job.jobId)
            .set(Tables.JOBS.JOB_TITLE, job.jobTitle)
            .set(Tables.JOBS.COMPANY_NAME, job.companyName)
            .set(Tables.JOBS.URL, job.url)
            .set(Tables.JOBS.LOCATION, job.location)
            .set(Tables.JOBS.PLACE_ID, job.placeId)
            .set(Tables.JOBS.SALARY, job.salary)
            .set(Tables.JOBS.WORK_STYLE, job.workStyle?.name)
            .set(Tables.JOBS.STATUS, job.status.name)
            .set(Tables.JOBS.APPLIED_DATE, job.appliedDate)
            .set(Tables.JOBS.JOB_BOARD, job.jobBoard)
            .set(Tables.JOBS.NOTE, job.note)
            .set(Tables.JOBS.BOARD_ID, job.boardId)
            .where(Tables.JOBS.JOB_ID.eq(job.jobId))
            .execute()
    }

    override fun delete(jobId: String) {
        jooq.deleteFrom(Tables.JOBS)
            .where(Tables.JOBS.JOB_ID.eq(jobId))
            .execute()
    }

    override fun fetchByBoardId(boardId: String): List<Job> {
        return jooq.selectFrom(Tables.JOBS)
            .where(Tables.JOBS.BOARD_ID.eq(boardId))
            .orderBy(Tables.JOBS.ADDED_DATETIME.desc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    override fun fetchByBoardIdWithInterview(boardId: String): List<JobWithInterviewDto> {
        return jooq.select(
            Tables.JOBS.JOB_ID,
            Tables.JOBS.JOB_TITLE,
            Tables.JOBS.COMPANY_NAME,
            Tables.JOBS.URL,
            Tables.JOBS.LOCATION,
            Tables.JOBS.SALARY,
            Tables.JOBS.WORK_STYLE,
            Tables.JOBS.STATUS,
            Tables.JOBS.APPLIED_DATE,
            Tables.JOBS.JOB_BOARD,
            Tables.JOBS.NOTE,
            Tables.JOBS.BOARD_ID,
            Tables.JOBS.ADDED_DATETIME,
            Tables.INTERVIEWS.INTERVIEW_ID,
            Tables.INTERVIEWS.TITLE,
            Tables.INTERVIEWS.INTERVIEW_DATETIME,
            Tables.INTERVIEWS.NOTE,
            Tables.INTERVIEWS.COMPLETED,
            Tables.INTERVIEWS.JOB_ID,
            Tables.INTERVIEWS.ACTIVITY_ID
        )
            .from(Tables.JOBS)
            .innerJoin(Tables.INTERVIEWS)
            .on(Tables.JOBS.JOB_ID.eq(Tables.INTERVIEWS.JOB_ID))
            .where(Tables.JOBS.BOARD_ID.eq(boardId))
            .and(Tables.INTERVIEWS.COMPLETED.isFalse)
            .orderBy(Tables.INTERVIEWS.INTERVIEW_DATETIME.asc())
            .fetch()
            .mapNotNull {
                recordToJobWithInterviewDto(it)
            }
    }

    override fun fetchByBoardIdWithAssignment(boardId: String): List<JobWithAssignmentDto> {
        return jooq.select(
            Tables.JOBS.JOB_ID,
            Tables.JOBS.JOB_TITLE,
            Tables.JOBS.COMPANY_NAME,
            Tables.JOBS.URL,
            Tables.JOBS.LOCATION,
            Tables.JOBS.SALARY,
            Tables.JOBS.WORK_STYLE,
            Tables.JOBS.STATUS,
            Tables.JOBS.APPLIED_DATE,
            Tables.JOBS.JOB_BOARD,
            Tables.JOBS.NOTE,
            Tables.JOBS.BOARD_ID,
            Tables.JOBS.ADDED_DATETIME,
            Tables.ASSIGNMENTS.ASSIGNMENT_ID,
            Tables.ASSIGNMENTS.TITLE,
            Tables.ASSIGNMENTS.DEADLINE_DATETIME,
            Tables.ASSIGNMENTS.NOTE,
            Tables.ASSIGNMENTS.COMPLETED,
            Tables.ASSIGNMENTS.JOB_ID,
            Tables.ASSIGNMENTS.ACTIVITY_ID
        )
            .from(Tables.JOBS)
            .innerJoin(Tables.ASSIGNMENTS)
            .on(Tables.JOBS.JOB_ID.eq(Tables.ASSIGNMENTS.JOB_ID))
            .where(Tables.JOBS.BOARD_ID.eq(boardId))
            .and(Tables.ASSIGNMENTS.COMPLETED.isFalse)
            .orderBy(Tables.ASSIGNMENTS.DEADLINE_DATETIME.asc())
            .fetch()
            .mapNotNull {
                recordToJobWithAssignmentDto(it)
            }
    }

    override fun fetchByBoardIdAndStatus(boardId: String, status: Status): List<Job> {
        return jooq.selectFrom(Tables.JOBS)
            .where(Tables.JOBS.BOARD_ID.eq(boardId))
            .and(Tables.JOBS.STATUS.eq(status.name))
            .orderBy(Tables.JOBS.ADDED_DATETIME.desc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    private fun recordToEntity(record: JobsRecord): Job {
        return Job(
            jobId = record.jobId,
            jobTitle = record.jobTitle,
            companyName = record.companyName,
            url = record.url,
            location = record.location,
            placeId = record.placeId,
            salary = record.salary,
            workStyle = record.workStyle?.let { WorkStyle.valueOf(it) },
            status = Status.valueOf(record.status),
            appliedDate = record.appliedDate,
            jobBoard = record.jobBoard,
            note = record.note,
            addedDatetime = record.addedDatetime,
            boardId = record.boardId
        )
    }

    private fun recordToJobWithInterviewDto(record: Record): JobWithInterviewDto {
        val jobRecord = record.into(Tables.JOBS)
        val interviewRecord = record.into(Tables.INTERVIEWS)
        return JobWithInterviewDto(
            job = Job(
                jobId = jobRecord.jobId,
                jobTitle = jobRecord.jobTitle,
                companyName = jobRecord.companyName,
                url = jobRecord.url,
                location = jobRecord.location,
                placeId = jobRecord.placeId,
                salary = jobRecord.salary,
                workStyle = jobRecord.workStyle?.let { WorkStyle.valueOf(it) },
                status = Status.valueOf(jobRecord.status),
                appliedDate = jobRecord.appliedDate,
                jobBoard = jobRecord.jobBoard,
                note = jobRecord.note,
                addedDatetime = jobRecord.addedDatetime,
                boardId = jobRecord.boardId
            ),
            interview = Interview(
                interviewId = interviewRecord.interviewId,
                title = interviewRecord.title,
                interviewDateTime = interviewRecord.interviewDatetime,
                note = interviewRecord.note,
                completed = interviewRecord.completed,
                jobId = interviewRecord.jobId,
                activityId = interviewRecord.activityId
            ),
            tags = getInterviewTags(interviewRecord.interviewId)
        )
    }

    private fun getInterviewTags(interviewId: String): List<TagName> {
        return jooq.select(Tables.INTERVIEW_TAGS.NAME)
            .from(Tables.INTERVIEW_TAGS)
            .where(Tables.INTERVIEW_TAGS.INTERVIEW_ID.eq(interviewId))
            .fetch(Tables.INTERVIEW_TAGS.NAME)
            .mapNotNull { TagName.valueOf(it) }
    }

    private fun recordToJobWithAssignmentDto(record: Record): JobWithAssignmentDto {
        val jobRecord = record.into(Tables.JOBS)
        val assignmentRecord = record.into(Tables.ASSIGNMENTS)
        return JobWithAssignmentDto(
            job = Job(
                jobId = jobRecord.jobId,
                jobTitle = jobRecord.jobTitle,
                companyName = jobRecord.companyName,
                url = jobRecord.url,
                location = jobRecord.location,
                placeId = jobRecord.placeId,
                salary = jobRecord.salary,
                workStyle = jobRecord.workStyle?.let { WorkStyle.valueOf(it) },
                status = Status.valueOf(jobRecord.status),
                appliedDate = jobRecord.appliedDate,
                jobBoard = jobRecord.jobBoard,
                note = jobRecord.note,
                addedDatetime = jobRecord.addedDatetime,
                boardId = jobRecord.boardId
            ),
            assignment = Assignment(
                assignmentId = assignmentRecord.assignmentId,
                title = assignmentRecord.title,
                deadlineDatetime = assignmentRecord.deadlineDatetime,
                note = assignmentRecord.note,
                completed = assignmentRecord.completed,
                jobId = assignmentRecord.jobId,
                activityId = assignmentRecord.activityId
            )
        )
    }
}
