package com.example.job_app.presentation.shared

import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Component
class RequestHeaderContext {
    fun getBoardId(): String? {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes)
            .request
        return request.getHeader("Board-Id")
    }
}
