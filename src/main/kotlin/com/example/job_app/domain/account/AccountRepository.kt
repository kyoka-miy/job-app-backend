package com.example.job_app.domain.account

import com.example.job_app.presentation.controller.AccountDto
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository {
    fun insert(account: Account)
    fun findByEmail(email: String): Account?
    fun findAll(): List<AccountDto>
}
