package com.example.job_app.domain.shared

class DomainException(
    val errorCode: DomainErrorCode,
    override val message: String
) : RuntimeException(message)
