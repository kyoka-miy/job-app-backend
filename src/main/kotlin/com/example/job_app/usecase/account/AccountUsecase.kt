package com.example.job_app.usecase.account

import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.jwt.JwtService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AccountUsecase(
    private val accountRepository: AccountRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {
    fun execute(
        email: String,
        name: String,
        password: String,
        role: Role = Role.USER
    ): String {
        val account = Account(
            null,
            LocalDateTime.now(),
            email,
            passwordEncoder.encode(password),
            name,
            role
        )
        accountRepository.insert(account)
        return jwtService.generateToken(account)
    }
}
