package com.example.job_app.presentation.auth

import org.springframework.stereotype.Component

@Component
class ExpiredTokenOnMemoryRepository : ExpiredTokenRepository {
    // JWT cannot be changed inside, so make a set on a memory to keep track of logged out jwt
    // reset when the server terminated and deployed
    private val tokens: MutableSet<String> = mutableSetOf()
    override fun put(token: String) {
        tokens.add(token)
        println(tokens)
    }

    override fun doesExists(token: String): Boolean = tokens.contains(token)
}
