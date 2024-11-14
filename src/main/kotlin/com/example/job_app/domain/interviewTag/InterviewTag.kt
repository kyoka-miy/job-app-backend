package com.example.job_app.domain.interviewTag

data class InterviewTag(
    val interviewId: String,
    val name: TagName
)

enum class TagName {
    PHONE_SCREEN,
    FIRST_INTERVIEW,
    SECOND_INTERVIEW,
    THIRD_INTERVIEW,
    FINAL_INTERVIEW,
    LEETCODE,
    LIVE_CODING,
    TECH_INTERVIEW,
    HOMEWORK,
    HR_INTERVIEW
}
