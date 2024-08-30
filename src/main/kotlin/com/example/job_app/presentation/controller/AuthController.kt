package com.example.job_app.presentation.controller

import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.account.AccountUsecase
import com.example.job_app.usecase.auth.AuthUsecase
import com.example.job_app.usecase.logout.LogoutService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1")
class AuthController(
    private val authUsecase: AuthUsecase,
    private val logoutService: LogoutService,
    private val accountUsecase: AccountUsecase
) {
    @PostMapping("/register")
    fun register(
        @RequestBody @Validated
        request: AccountCreateRequest
    ): String {
        val role = request.role ?: Role.USER
        return accountUsecase.execute(
            request.email,
            request.name,
            request.password,
            role
        )
    }

    @GetMapping("/login")
    fun login(
        @RequestBody @Validated
        request: LoginRequest
    ): String {
        return authUsecase.execute(request.email, request.password)
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): String {
        logoutService.logout(request)
        return "Successfully logged out"
    }
}

data class AccountCreateRequest(
    @field:NotBlank val email: String,
    @field:NotBlank val name: String,
    @field:NotBlank val password: String,
    val role: Role?
)
data class LoginRequest(
    @field:NotBlank val email: String,
    @field:NotBlank val password: String
)
