package com.example.job_app.usecase.board

import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.board.Board
import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetBoardsUseCase(
    private val boardRepository: BoardRepository,
    private val accountRepository: AccountRepository
) {
    fun execute(accountId: String): List<Board> {
        val account = accountRepository.fetch(accountId)
            ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Account not found")
        return boardRepository.fetchByAccountId(account.accountId)
    }
}
