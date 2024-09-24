package com.example.job_app.domain.shared

data class DomainErrorCode(val value: String)
object DomainErrorCodes {
    object Common {
        val duplicate = DomainErrorCode("Common.duplicate")
    }
    object AccountRegister {
        val duplicate = DomainErrorCode("AccountRegister.duplicate")
    }
}
