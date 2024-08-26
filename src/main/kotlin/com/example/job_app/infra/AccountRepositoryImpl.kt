package com.example.job_app.infra

import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.ktknowledgeTodo.infra.jooq.Tables.ACCOUNTS
import com.example.ktknowledgeTodo.infra.jooq.tables.records.AccountsRecord
import org.jooq.DSLContext
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AccountRepositoryImpl(
    private val jooq: DSLContext
) : AccountRepository {
    override fun insert(account: Account) {
        jooq.insertInto(ACCOUNTS)
            .set(ACCOUNTS.ACCOUNT_ID, account.accountId)
            .set(ACCOUNTS.REGISTERED_DATETIME, account.registeredDatetime)
            .set(ACCOUNTS.EMAIL, account.email)
            .set(ACCOUNTS.NAME, account.name)
            .set(ACCOUNTS.PASSWORD, account._password)
            .set(ACCOUNTS.ROLE, account.role.name)
            .execute()
    }

    override fun findByEmail(email: String): Account {
        return jooq.selectFrom(ACCOUNTS)
            .where(ACCOUNTS.EMAIL.eq(email))
            .fetchOne {
                recordToEntity(it)
            } ?: throw UsernameNotFoundException("No user found with email: $email")
    }

    private fun recordToEntity(record: AccountsRecord): Account {
        return Account(
            accountId = record.accountId,
            registeredDatetime = record.registeredDatetime,
            email = record.email,
            _password = record.password,
            name = record.name,
            role = Role.valueOf(record.role)
        )
    }
}
