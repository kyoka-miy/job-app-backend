package com.example.job_app.infra.Job

import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import com.example.job_app.domain.job.WorkStyle
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.JobsRecord
import org.jooq.DSLContext
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
}
