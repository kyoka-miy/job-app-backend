package com.example.job_app.usecase.logout

import com.example.job_app.presentation.auth.ExpiredTokenRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class LogoutService(
    private val expiredTokenRepository: ExpiredTokenRepository
) {
    fun logout(request: HttpServletRequest) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }
        val jwt = authHeader.substring(7)
        expiredTokenRepository.put(jwt)
        SecurityContextHolder.clearContext()
    }
}
