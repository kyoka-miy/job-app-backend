package com.example.job_app.infra.job_list

import com.example.job_app.domain.job_list.JobList
import com.example.job_app.domain.job_list.JobListRepository
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.JobListsRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class JobListRepositoryImpl(
    private val jooq: DSLContext
) : JobListRepository {
    override fun fetch(): List<JobList> {
        return jooq.selectFrom(Tables.JOB_LISTS)
            .fetch()
            .map {
                recordToEntity(it)
                // test
            }
    }

    override fun fetchById(jobListId: String): JobList? {
        return jooq.selectFrom(Tables.JOB_LISTS)
            .where(Tables.JOB_LISTS.JOB_LIST_ID.eq(jobListId))
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun update(jobList: JobList) {
        jooq.update(Tables.JOB_LISTS)
            .set(Tables.JOB_LISTS.JOB_LIST_ID, jobList.jobListId)
            .set(Tables.JOB_LISTS.ACTIVE, jobList.active)
            .where(Tables.JOB_LISTS.JOB_LIST_ID.eq(jobList.jobListId))
            .execute()
    }

    private fun recordToEntity(record: JobListsRecord): JobList {
        return JobList(
            jobListId = record.jobListId,
            active = record.active
        )
    }
}
