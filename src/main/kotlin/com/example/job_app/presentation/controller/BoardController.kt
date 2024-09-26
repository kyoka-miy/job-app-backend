package com.example.job_app.presentation.controller

import com.example.job_app.domain.board.Board
import com.example.job_app.presentation.auth.AccountSessionProvider
import com.example.job_app.usecase.board.AddBoardUseCase
import com.example.job_app.usecase.board.DeleteBoardUseCase
import com.example.job_app.usecase.board.GetBoardsUseCase
import com.example.job_app.usecase.board.UpdateBoardUseCase
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boards")
class BoardController(
    private val accountSessionProvider: AccountSessionProvider,
    private val getBoardsUseCase: GetBoardsUseCase,
    private val addBoardUseCase: AddBoardUseCase,
    private val updateBoardUseCase: UpdateBoardUseCase,
    private val deleteBoardUseCase: DeleteBoardUseCase
) {
    @GetMapping
    fun getBoards(): List<Board> {
        return getBoardsUseCase.execute(accountSessionProvider.getAccountSession())
    }

    @PostMapping
    fun addBoard(
        @RequestBody @Validated
        request: AddOrUpdateBoardRequest
    ) {
        addBoardUseCase.execute(accountSessionProvider.getAccountSession(), request.name)
    }

    @PutMapping("/{boardId}")
    fun updateBoard(
        @PathVariable("boardId") boardId: String,
        @RequestBody @Validated
        request: AddOrUpdateBoardRequest
    ) {
        updateBoardUseCase.execute(boardId, request.name)
    }

    @DeleteMapping("/{boardId}")
    fun deleteBoard(@PathVariable("boardId") boardId: String) {
        deleteBoardUseCase.execute(boardId)
    }
}

data class AddOrUpdateBoardRequest(
    @field:NotBlank
    val name: String
)
