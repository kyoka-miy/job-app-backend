package com.example.job_app.presentation.controller

import com.example.job_app.usecase.account.RegisterAccountUsecase
import com.example.job_app.usecase.account.TokenResponseDto
import com.example.job_app.usecase.auth.AuthUsecase
import jakarta.validation.constraints.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authUsecase: AuthUsecase,
    private val registerAccountUsecase: RegisterAccountUsecase
) {
    @PostMapping("/register")
    fun register(
        @RequestBody @Validated
        request: AccountCreateRequest
    ): TokenResponseDto {
        return registerAccountUsecase.execute(
            request.email,
            request.name,
            request.password,
            request.role
        )
    }

    @GetMapping("/login")
    fun login(
        @Validated
        request: LoginRequest
    ): TokenResponseDto {
        return authUsecase.execute(request.email, request.password)
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
    val role: String = "USER"
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
