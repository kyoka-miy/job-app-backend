package com.example.job_app.domain.board

import com.example.job_app.domain.account.Role
import com.example.job_app.domain.shared.IdGenerator
import java.time.LocalDateTime

data class Board(
    val boardId: String = IdGenerator.generate(),
    val createdDatetime: LocalDateTime,
    var name: String,
    val accountId: String
)
