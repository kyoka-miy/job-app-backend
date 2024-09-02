package com.example.job_app.usecase.shared

class UseCaseException(
    val errorCode: String,
    override val message: String
) : RuntimeException(message)
