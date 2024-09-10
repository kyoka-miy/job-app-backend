package com.example.job_app.usecase.account

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.presentation.controller.AccountDto
import org.springframework.stereotype.Component

@Component
class GetAccountsUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(): List<AccountDto> {
        return accountRepository.findAll()
    }
}
