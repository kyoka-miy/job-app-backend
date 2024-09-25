package com.example.job_app.presentation.controller

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interview.InterviewStage
import com.example.job_app.usecase.interview.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/interviews")
class InterviewController(
    private val addInterviewUseCase: AddInterviewUseCase,
    private val deleteInterviewUseCase: DeleteInterviewUseCase,
    private val updateInterviewUseCase: UpdateInterviewUseCase,
    private val getInterviewsUseCase: GetInterviewsUseCase,
    private val inactivateInterviewUseCase: InactivateInterviewUseCase
) {
    @PostMapping("/{jobId}")
    fun addInterview(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated
        request: AddOrUpdateInterviewRequest
    ) {
        addInterviewUseCase.execute(jobId, request.interviewDateTime, request.stage, request.type, request.note)
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
        updateInterviewUseCase.execute(interviewId, request.interviewDateTime, request.stage, request.type, request.note)
    }

    @PutMapping("/{interviewId}/inactivate")
    fun inactivateInterview(
        @PathVariable("interviewId") interviewId: String
    ) {
        inactivateInterviewUseCase.execute(interviewId)
    }

    @GetMapping("/{jobId}")
    fun getInterviews(
        @PathVariable("jobId") jobId: String
    ): List<Interview> {
        return getInterviewsUseCase.execute(jobId)
    }
}

data class AddOrUpdateInterviewRequest(
    @field:NotNull
    val interviewDateTime: LocalDateTime,
    @field:NotNull
    val stage: InterviewStage,
    @field:NotBlank
    val type: String,
    val note: String?
)
