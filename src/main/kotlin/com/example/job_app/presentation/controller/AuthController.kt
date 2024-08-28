package com.example.job_app.presentation.controller

import com.example.job_app.usecase.auth.AuthUsecase
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authUsecase: AuthUsecase
) {
    @GetMapping("/login")
    fun login(
        @RequestBody @Validated
        request: LoginRequest
    ): String {
        return authUsecase.execute(request.email, request.password)
    }
}

data class LoginRequest(
    @field:NotBlank val email: String,
    @field:NotBlank val password: String
)
