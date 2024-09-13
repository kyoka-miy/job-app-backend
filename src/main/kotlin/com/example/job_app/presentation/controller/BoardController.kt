package com.example.job_app.presentation.controller

import com.example.job_app.domain.board.Board
import com.example.job_app.presentation.auth.AccountSessionProvider
import com.example.job_app.usecase.board.GetBoardsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boards")
class BoardController(
    private val accountSessionProvider: AccountSessionProvider,
    private val getBoardsUseCase: GetBoardsUseCase
) {
    @GetMapping
    fun getBoards(): List<Board> {
        return getBoardsUseCase.execute(accountSessionProvider.getAccountSession())
    }
}
