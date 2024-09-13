package com.example.job_app.usecase.shared

data class UseCaseErrorCode(val value: String)

object UseCaseErrorCodes {
    object AccountRegister {
        val emailDuplicate = UseCaseErrorCode("accountRegister.emailDuplicate")
        val invalidRole = UseCaseErrorCode("accountRegister.invalidRole")
    }

    object Login {
        val wrongPassword = UseCaseErrorCode("login.wrongPassword")
        val emailNotFound = UseCaseErrorCode("login.emailNotFound")
    }

    object GetAccount {
        val emailNotFound = UseCaseErrorCode("getAccount.emailNotFound")
    }
}
