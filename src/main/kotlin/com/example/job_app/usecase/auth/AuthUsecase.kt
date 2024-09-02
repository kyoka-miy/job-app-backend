package com.example.job_app.usecase.auth

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.usecase.jwt.JwtService
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
class AuthUsecase(
    private val authenticationManager: AuthenticationManager,
    private val accountRepository: AccountRepository,
    private val jwtService: JwtService
) {
    fun execute(email: String, password: String): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                email,
                password
            )
        )
        val account = accountRepository.findByEmail(email) ?: throw UseCaseException("MailNotFound", "Mail address not found")
        return jwtService.generateToken(account)
    }
}
