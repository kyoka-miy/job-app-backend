package com.example.job_app.presentation.controller

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interviewTag.TagName
import com.example.job_app.usecase.interview.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/interviews")
class InterviewController(
    private val addInterviewUseCase: AddInterviewUseCase,
    private val deleteInterviewUseCase: DeleteInterviewUseCase,
    private val updateInterviewUseCase: UpdateInterviewUseCase,
    private val getInterviewsUseCase: GetInterviewsUseCase
) {
    @PostMapping("/jobs/{jobId}")
    fun addInterview(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated
        request: AddOrUpdateInterviewRequest
    ) {
        addInterviewUseCase.execute(
            jobId,
            request.title,
            request.tags,
            request.interviewDateTime,
            request.note,
            request.completed
        )
    }

    @DeleteMapping("/{interviewId}")
    fun deleteInterview(@PathVariable("interviewId") interviewId: String) {
        deleteInterviewUseCase.execute(interviewId)
    }

    @PutMapping("/{interviewId}")
    fun updateInterview(
        @PathVariable("interviewId") interviewId: String,
        @RequestBody @Validated
        request: AddOrUpdateInterviewRequest
    ) {
        updateInterviewUseCase.execute(interviewId, request.title, request.tags, request.interviewDateTime, request.note, request.completed)
    }

    @GetMapping("/{jobId}")
    fun getInterviews(
        @PathVariable("jobId") jobId: String
    ): List<Interview> {
        return getInterviewsUseCase.execute(jobId)
    }

    // TODO: get Upcoming interviews with boardID
}

data class AddOrUpdateInterviewRequest(
    val title: String,
    val tags: List<TagName>?,
    val interviewDateTime: LocalDateTime,
    val note: String?,
    val completed: Boolean
)
