package com.example.job_app.presentation.auth

import com.example.job_app.domain.account.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class ApplicationConfig(
    private val accountRepository: AccountRepository
) {
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String ->
            accountRepository.findByEmail(username)
        }
    }
}
