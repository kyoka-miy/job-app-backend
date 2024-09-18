package com.example.job_app.usecase.board

import com.example.job_app.domain.board.Board
import com.example.job_app.domain.board.BoardRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AddBoardUseCase(
    private val boardRepository: BoardRepository
) {
    fun execute(accountId: String, name: String) {
        boardRepository.insert(
            Board(
                name = name,
                createdDatetime = LocalDateTime.now(),
                accountId = accountId
            )
        )
    }
}
