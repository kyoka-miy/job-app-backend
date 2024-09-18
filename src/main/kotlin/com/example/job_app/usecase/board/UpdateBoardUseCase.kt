package com.example.job_app.usecase.board

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class UpdateBoardUseCase(
    private val boardRepository: BoardRepository
) {
    fun execute(boardId: String, name: String) {
        val board = boardRepository.fetch(boardId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Board not found")
        board.name = name
        boardRepository.update(board)
    }
}
