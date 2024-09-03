package com.example.job_app.usecase.shared

class UseCaseException(
    val errorCode: UseCaseErrorCode,
    override val message: String
) : RuntimeException(message)
