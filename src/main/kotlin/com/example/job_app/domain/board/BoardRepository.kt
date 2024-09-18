package com.example.job_app.domain.board

import org.springframework.stereotype.Repository

@Repository
interface BoardRepository {
    fun fetchByAccountId(accountId: String): List<Board>
    fun fetch(boardId: String): Board?
    fun insert(board: Board)
    fun update(board: Board)
    fun delete(boardId: String)
}
