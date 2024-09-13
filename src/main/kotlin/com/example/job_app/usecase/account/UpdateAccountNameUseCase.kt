package com.example.job_app.usecase.account

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class UpdateAccountNameUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(email: String, name: String) {
        val account = accountRepository.findByEmail(email)
            ?: throw UseCaseException(UseCaseErrorCodes.GetAccount.emailNotFound, "Email not found")
        account.name = name
        accountRepository.update(account)
    }
}
