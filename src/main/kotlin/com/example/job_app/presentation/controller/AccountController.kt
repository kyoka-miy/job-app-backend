package com.example.job_app.presentation.controller

import com.example.job_app.presentation.auth.AccountSessionProvider
import com.example.job_app.usecase.account.DeleteAccountUseCase
import com.example.job_app.usecase.account.GetAccountUseCase
import com.example.job_app.usecase.account.UpdateAccountNameUseCase
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val getAccountUseCase: GetAccountUseCase,
    private val updateAccountNameUseCase: UpdateAccountNameUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val accountSessionProvider: AccountSessionProvider
) {
    @GetMapping
    fun getAccount(): AccountDto {
        return getAccountUseCase.execute(accountSessionProvider.getAccountSession())
    }

    @PutMapping
    fun updateAccountName(
        @RequestBody @Validated
        request: AccountUpdateRequest
    ) {
        updateAccountNameUseCase.execute(accountSessionProvider.getAccountSession(), request.name)
    }

    @DeleteMapping
    fun deleteAccount() {
        deleteAccountUseCase.execute(accountSessionProvider.getAccountSession())
    }
}

data class AccountUpdateRequest(
    @field:NotBlank(message = "Name cannot be empty")
    val name: String
)
