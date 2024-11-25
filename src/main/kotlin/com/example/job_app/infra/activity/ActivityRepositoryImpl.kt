package com.example.job_app.infra.activity

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.ActivitiesRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class ActivityRepositoryImpl(
    private val jooq: DSLContext
) : ActivityRepository {
    override fun fetchByJobId(jobId: String): List<Activity> {
        return jooq.selectFrom(Tables.ACTIVITIES)
            .where(Tables.ACTIVITIES.JOB_ID.eq(jobId))
            .and(Tables.ACTIVITIES.DELETED.isFalse)
            .orderBy(Tables.ACTIVITIES.ACTIVITY_DATETIME.asc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    override fun fetch(activityId: String): Activity? {
        return jooq.selectFrom(Tables.ACTIVITIES)
            .where(Tables.ACTIVITIES.ACTIVITY_ID.eq(activityId))
            .and(Tables.ACTIVITIES.DELETED.isFalse)
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun delete(activityId: String) {
        jooq.update(Tables.ACTIVITIES)
            .set(Tables.ACTIVITIES.DELETED, true)
            .where(Tables.ACTIVITIES.ACTIVITY_ID.eq(activityId))
            .execute()
    }

    override fun insert(activity: Activity) {
        try {
            jooq.insertInto(Tables.ACTIVITIES)
                .set(Tables.ACTIVITIES.ACTIVITY_ID, activity.activityId)
                .set(Tables.ACTIVITIES.NAME, activity.name)
                .set(Tables.ACTIVITIES.ACTIVITY_DATETIME, activity.activityDateTime)
                .set(Tables.ACTIVITIES.DELETED, activity.deleted)
                .set(Tables.ACTIVITIES.JOB_ID, activity.jobId)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.Common.duplicate, "Duplicate key")
        }
    }

    override fun update(activity: Activity) {
        jooq.update(Tables.ACTIVITIES)
            .set(Tables.ACTIVITIES.NAME, activity.name)
            .set(Tables.ACTIVITIES.ACTIVITY_DATETIME, activity.activityDateTime)
            .set(Tables.ACTIVITIES.DELETED, activity.deleted)
            .execute()
    }

    private fun recordToEntity(record: ActivitiesRecord): Activity {
        return Activity(
            activityId = record.activityId,
            name = record.name,
            activityDateTime = record.activityDatetime,
            deleted = record.deleted,
            jobId = record.jobId
        )
    }
}
