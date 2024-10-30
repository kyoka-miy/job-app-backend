package com.example.job_app.usecase.shared

data class UseCaseErrorCode(val value: String)

object UseCaseErrorCodes {
    object Common {
        val idNotFound = UseCaseErrorCode("idNotFound")
    }
    object AccountRegister {
        val emailDuplicate = UseCaseErrorCode("accountRegister.emailDuplicate")
        val invalidRole = UseCaseErrorCode("accountRegister.invalidRole")
    }
    object Login {
        val wrongPassword = UseCaseErrorCode("login.wrongPassword")
    }
    object Offer {
        val invalidDateTime = UseCaseErrorCode("offer.invalidDateTime")
    }
    object Place {
        val badRequest = UseCaseErrorCode("place.badRequest")
        val unauthorized = UseCaseErrorCode("place.unauthorized")
        val forbidden = UseCaseErrorCode("place.forbidden")
        val resourceNotFound = UseCaseErrorCode("place.resourceNotFound")
        val internalServerError = UseCaseErrorCode("place.internalServerError")
        val other = UseCaseErrorCode("place.other")
    }
}
