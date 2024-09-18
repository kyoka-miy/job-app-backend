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
    fun execute(accountId: String): AccountDto {
        val account = accountRepository.fetch(accountId)
            ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Account not found")
        return AccountDto(
            accountId = account.accountId,
            email = account.email,
            name = account.name,
            registeredDatetime = account.registeredDatetime,
            role = account.role
        )
    }
}
