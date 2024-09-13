package com.example.job_app.presentation.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class AccountSessionProvider {
    fun getAccountSession(): String {
        try {
            val authentication = SecurityContextHolder.getContext().authentication
            val userDetails = authentication.principal as UserDetails
            return userDetails.username
        } catch (e: Exception) {
            throw e
        }
    }
}
