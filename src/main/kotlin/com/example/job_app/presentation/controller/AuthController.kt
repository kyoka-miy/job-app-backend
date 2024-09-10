package com.example.job_app.presentation.controller

import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.account.RegisterAccountUsecase
import com.example.job_app.usecase.auth.AuthUsecase
import com.example.job_app.usecase.logout.LogoutService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authUsecase: AuthUsecase,
    private val logoutService: LogoutService,
    private val registerAccountUsecase: RegisterAccountUsecase
) {
    @PostMapping("/register")
    fun register(
        @RequestBody @Validated
        request: AccountCreateRequest
    ): String {
        return registerAccountUsecase.execute(
            request.email,
            request.name,
            request.password,
            request.role
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
    @field:NotBlank(message = "required mail address")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Name cannot be empty")
    val name: String,
    @field:NotBlank(message = "Password cannot be empty")
    @field:Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @field:Pattern(regexp = "[\\x21-\\x7E]+", message = "Password must contain only alphanumeric characters and symbols")
    val password: String,
    val role: Role?
)
data class LoginRequest(
    @field:NotBlank(message = "required mail address")
    @field:Email(message = "Invalid email format")
    val email: String,
    @field:NotBlank(message = "Password cannot be empty")
    @field:Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @field:Pattern(regexp = "[\\x21-\\x7E]+", message = "Password must contain only alphanumeric characters and symbols")
    val password: String
)
