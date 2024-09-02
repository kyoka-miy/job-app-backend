package com.example.job_app.domain.shared

import java.util.*

// @Component has to be dependency injected to use in other component
object IdGenerator {
    fun generate(): String = UUID.randomUUID().toString()
}
