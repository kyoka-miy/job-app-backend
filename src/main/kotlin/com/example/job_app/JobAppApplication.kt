package com.example.job_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JobAppApplication

fun main(args: Array<String>) {
	runApplication<JobAppApplication>(*args)
}
