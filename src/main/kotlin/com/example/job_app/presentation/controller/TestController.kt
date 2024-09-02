package com.example.job_app.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/")
    fun test(): String {
        return "Hello World!"
    }

    @GetMapping("/test")
    fun test2(): String {
        return "Hello World!!!"
    }

    @GetMapping("/admin")
    fun test3(): String {
        return "You are ADMIN"
    }
}
