package com.example.job_app.usecase.interview

import com.example.job_app.domain.interview.Interview
import com.example.job_app.domain.interviewTag.TagName

data class InterviewWithTagsDto(
    val interview: Interview,
    val tags: List<TagName>
)
