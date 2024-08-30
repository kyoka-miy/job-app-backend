package com.example.job_app.presentation.auth

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class JooqConfig(private val dataSource: DataSource) {

    @Bean
    fun dslContext(): DSLContext {
        val settings = Settings()
        val configuration = DefaultConfiguration()
            .set(dataSource)
            .set(SQLDialect.MYSQL)
            .set(settings)

        return DSL.using(configuration)
    }
}
