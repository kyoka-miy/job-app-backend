package com.example.job_app.usecase

import com.example.job_app.TestConfig
import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.job_app.presentation.controller.AccountDto
import com.example.job_app.usecase.account.GetAccountsUseCase
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.time.LocalDateTime

@Import(TestConfig::class)
@SpringBootTest
@Transactional
class GetAccountsUseCaseTest(
    @Autowired private val accountRepository: AccountRepository,
    @Autowired private val getAccountsUseCase: GetAccountsUseCase
) {
    @Test
    fun ok() {
        val account1 = Account(
            email = "test1@mail.com",
            name = "account1",
            _password = "password",
            registeredDatetime = LocalDateTime.of(2024, 1, 1, 0, 0, 0),
            role = Role.USER
        )
        val account2 = Account(
            email = "test2@mail.com",
            name = "account2",
            _password = "password",
            registeredDatetime = LocalDateTime.of(2023, 1, 1, 0, 0, 0),
            role = Role.USER
        )
        val account3 = Account(
            email = "test3@mail.com",
            name = "account3",
            _password = "password",
            registeredDatetime = LocalDateTime.of(2022, 1, 1, 0, 0, 0),
            role = Role.ADMIN
        )
        accountRepository.insert(account1)
        accountRepository.insert(account2)
        accountRepository.insert(account3)

        getAccountsUseCase.execute() shouldBe listOf(
            AccountDto(
                accountId = account1.accountId,
                email = "test1@mail.com",
                name = "account1",
                registeredDatetime = LocalDateTime.of(2024, 1, 1, 0, 0, 0),
                role = Role.USER
            ),
            AccountDto(
                accountId = account2.accountId,
                email = "test2@mail.com",
                name = "account2",
                registeredDatetime = LocalDateTime.of(2023, 1, 1, 0, 0, 0),
                role = Role.USER
            ),
            AccountDto(
                accountId = account3.accountId,
                email = "test3@mail.com",
                name = "account3",
                registeredDatetime = LocalDateTime.of(2022, 1, 1, 0, 0, 0),
                role = Role.ADMIN
            )
        )
    }
}
