package com.example.job_app.presentation.controller

import com.example.job_app.domain.activity.Activity
import com.example.job_app.usecase.activity.AddActivityUseCase
import com.example.job_app.usecase.activity.DeleteActivityUseCase
import com.example.job_app.usecase.activity.GetActivitiesUseCase
import com.example.job_app.usecase.activity.UpdateActivityUseCase
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/activities")
class ActivityController(
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val deleteActivityUseCase: DeleteActivityUseCase,
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val addActivityUseCase: AddActivityUseCase
) {
    @GetMapping("/jobs/{jobId}")
    fun getActivities(@PathVariable("jobId") jobId: String): List<Activity> {
        return getActivitiesUseCase.execute(jobId)
    }

    @DeleteMapping("/{activityId}")
    fun deleteActivity(@PathVariable("activityId") activityId: String) {
        deleteActivityUseCase.execute(activityId)
    }

    @PostMapping("/jobs/{jobId}")
    fun addActivity(
        @PathVariable("jobId") jobId: String,
        @RequestBody request: UpdateOrAddActivityRequest
    ) {
        addActivityUseCase.execute(
            jobId,
            request.name,
            request.activityDateTime
        )
    }

    @PutMapping("/{activityId}")
    fun updateActivity(
        @PathVariable("activityId") activityId: String,
        @RequestBody request: UpdateOrAddActivityRequest
    ) {
        updateActivityUseCase.execute(
            activityId,
            request.name,
            request.activityDateTime
        )
    }
}

data class UpdateOrAddActivityRequest(
    val name: String,
    val activityDateTime: LocalDateTime
)
