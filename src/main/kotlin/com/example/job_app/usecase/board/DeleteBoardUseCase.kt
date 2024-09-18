package com.example.job_app.usecase.board

import com.example.job_app.domain.board.BoardRepository
import org.springframework.stereotype.Component

@Component
class DeleteBoardUseCase(
    private val boardRepository: BoardRepository
) {
    fun execute(boardId: String) {
        boardRepository.delete(boardId)
    }
}
