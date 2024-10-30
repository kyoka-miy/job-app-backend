package com.example.job_app.infra.place

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "place")
class PlaceProperties {
    lateinit var url: String
    lateinit var apiKey: String
}
