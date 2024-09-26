package com.example.job_app.domain.activity

interface ActivityRepository {
    fun fetchByJobId(jobId: String): List<Activity>
    fun fetch(activityId: String): Activity?
    fun delete(activityId: String)
    fun insert(activity: Activity)
    fun update(activity: Activity)
}
