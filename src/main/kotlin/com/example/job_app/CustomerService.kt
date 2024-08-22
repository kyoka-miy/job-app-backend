package com.example.job_app

import org.jooq.DSLContext
import org.jooq.Result
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val jooq: DSLContext
) {
    public fun fetchCustomer(): Result<CustomersRecord> {
        return jooq.selectFrom(CUSTOMERS).where(CUSTOMERS.CUSTOMER_ID.eq("")).fetch()
    }
}
