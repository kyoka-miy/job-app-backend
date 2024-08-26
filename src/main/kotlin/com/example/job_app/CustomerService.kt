package com.example.job_app

import com.example.ktknowledgeTodo.infra.jooq.Tables.ACCOUNTS
import com.example.ktknowledgeTodo.infra.jooq.tables.records.AccountsRecord
import org.jooq.DSLContext
import org.jooq.Result
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val jooq: DSLContext
) {
    public fun fetchCustomer(): Result<AccountsRecord> {
        return jooq.selectFrom(ACCOUNTS).where(ACCOUNTS.ACCOUNT_ID.eq(1)).fetch()
    }
}
