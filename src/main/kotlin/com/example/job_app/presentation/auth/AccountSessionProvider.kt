package com.example.job_app.presentation.auth

import com.example.job_app.domain.account.Account
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class AccountSessionProvider {
    fun getAccountSession(): String {
        try {
            val authentication = SecurityContextHolder.getContext().authentication
            val account = authentication.principal as Account
            return account.accountId
        } catch (e: Exception) {
            throw e
        }
    }
}
