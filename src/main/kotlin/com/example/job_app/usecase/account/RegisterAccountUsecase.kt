package com.example.job_app.usecase.account

import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.job_app.usecase.jwt.JwtService
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RegisterAccountUsecase(
    private val accountRepository: AccountRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {
    fun execute(
        email: String,
        name: String,
        password: String,
        role: Role?
    ): String {
        if (accountRepository.findByEmail(email) != null) {
            throw UseCaseException(UseCaseErrorCodes.AccountRegister.emailDuplicate, "This email is already registered")
        }

        val account = Account(
            registeredDatetime = LocalDateTime.now(),
            email = email,
            _password = passwordEncoder.encode(password),
            name = name,
            role = role ?: Role.USER
        )
        accountRepository.insert(account)
        return jwtService.generateToken(account)
    }
}
