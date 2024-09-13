package com.example.job_app.usecase.account

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.presentation.controller.AccountDto
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetAccountUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(email: String): AccountDto {
        val account = accountRepository.findByEmail(email)
            ?: throw UseCaseException(UseCaseErrorCodes.GetAccount.emailNotFound, "Email not found")
        return AccountDto(
            accountId = account.accountId,
            email = account.email,
            name = account.name,
            registeredDatetime = account.registeredDatetime,
            role = account.role
        )
    }
}
