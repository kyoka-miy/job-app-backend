package com.example.job_app.presentation.controller

import com.example.job_app.usecase.logout.LogoutService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutController(
    private val logoutService: LogoutService
) {
    @PostMapping("/account-logout")
    fun logout(request: HttpServletRequest): String {
        logoutService.logout(request)
        return "Successfully logged out"
    }
}
