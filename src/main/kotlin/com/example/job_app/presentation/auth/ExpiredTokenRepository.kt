package com.example.job_app.presentation.auth

interface ExpiredTokenRepository {
    fun put(token: String)
    fun doesExists(token: String): Boolean
}
