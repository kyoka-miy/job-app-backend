package com.example.job_app.usecase.account

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class UpdateAccountNameUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(accountId: String, name: String) {
        val account = accountRepository.fetch(accountId)
            ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Account not found")
        account.name = name
        accountRepository.update(account)
    }
}
