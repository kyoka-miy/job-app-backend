package com.example.job_app.domain.account

import org.springframework.stereotype.Repository

@Repository
interface AccountRepository {
    fun insert(account: Account)
    fun findByEmail(email: String): Account?
}
