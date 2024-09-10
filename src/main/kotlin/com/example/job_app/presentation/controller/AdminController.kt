package com.example.job_app.presentation.controller

import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.account.GetAccountsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/admin")
class AdminController(
    private val getAccountsUseCase: GetAccountsUseCase
) {

    @GetMapping
    fun test(): String {
        return "You are ADMIN!"
    }

    @GetMapping("/accounts")
    fun getAllAccounts(): List<AccountDto> {
        return getAccountsUseCase.execute()
    }
}

data class AccountDto(
    val accountId: String,
    val registeredDatetime: LocalDateTime,
    val email: String,
    val name: String,
    val role: Role
)
