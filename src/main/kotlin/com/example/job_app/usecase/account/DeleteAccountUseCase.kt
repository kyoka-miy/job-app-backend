package com.example.job_app.usecase.account

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteAccountUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(accountId: String) {
        val account = accountRepository.fetch(accountId)
            ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Account not found")
        accountRepository.delete(account)
    }
}
