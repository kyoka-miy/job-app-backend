package com.example.job_app

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@TestConfiguration
class TestConfig {
    // To rollback test data after each test
    @Bean
    fun dataSource(): DataSource {
        return TransactionAwareDataSourceProxy(
            DataSourceBuilder
                .create()
                .username(System.getenv("JDBC_DATABASE_USERNAME") ?: "user")
                .password(System.getenv("JDBC_DATABASE_PASSWORD") ?: "password")
                .url(System.getenv("JDBC_DATABASE_URL") ?: "jdbc:mysql://localhost:3307/cqq2l0ixquavsq6l")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build()
        )
    }
}
