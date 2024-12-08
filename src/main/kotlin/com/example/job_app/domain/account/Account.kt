package com.example.job_app.domain.account

import com.example.job_app.domain.shared.IdGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class Account(
    val accountId: String = IdGenerator.generate(),
    val registeredDatetime: LocalDateTime,
    var email: String,
    val _password: String,
    var name: String,
    val role: Role
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getUsername(): String {
        return email
    }
}
