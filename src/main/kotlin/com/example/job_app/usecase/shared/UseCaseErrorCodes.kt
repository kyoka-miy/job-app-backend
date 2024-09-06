package com.example.job_app.usecase.shared

data class UseCaseErrorCode(val value: String)

object UseCaseErrorCodes {
    object AccountRegister {
        val emailDuplicate = UseCaseErrorCode("accountRegister.emailDuplicate")
    }

    object Login {
        val emailNotFound = UseCaseErrorCode("login.emailNotFound")
    }
}
