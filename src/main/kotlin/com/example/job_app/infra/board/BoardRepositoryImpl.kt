package com.example.job_app.infra.board

import com.example.job_app.domain.board.Board
import com.example.job_app.domain.board.BoardRepository
import com.example.ktknowledgeTodo.infra.jooq.Tables
import com.example.ktknowledgeTodo.infra.jooq.tables.records.BoardsRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class BoardRepositoryImpl(
    private val jooq: DSLContext
) : BoardRepository {
    override fun fetchByAccountId(accountId: String): List<Board> {
        return jooq.selectFrom(Tables.BOARDS)
            .where(Tables.BOARDS.ACCOUNT_ID.eq(accountId))
            .orderBy(Tables.BOARDS.CREATED_DATETIME.desc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    override fun insert(board: Board) {
        TODO("Not yet implemented")
    }

    override fun update(board: Board) {
        TODO("Not yet implemented")
    }

    private fun recordToEntity(record: BoardsRecord): Board {
        return Board(
            boardId = record.boardId,
            createdDatetime = record.createdDatetime,
            name = record.name,
            accountId = record.accountId
        )
    }
}
