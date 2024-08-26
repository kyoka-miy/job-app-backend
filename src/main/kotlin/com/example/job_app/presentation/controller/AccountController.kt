package com.example.job_app.presentation.controller

import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.account.AccountUsecase
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountUsecase: AccountUsecase
) {
    @PostMapping("/register")
    fun register(
        @RequestBody request: AccountCreateRequest
    ): String {
        val role = request.role ?: Role.USER
        return accountUsecase.execute(
            request.email,
            request.name,
            request.password,
            role
        )
    }
}

data class AccountCreateRequest(
    @field:NotBlank val email: String,
    @field:NotBlank val name: String,
    @field:NotBlank val password: String,
    val role: Role?
)
