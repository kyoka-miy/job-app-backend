package com.example.job_app.infra.board

import com.example.job_app.domain.board.Board
import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
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

    override fun fetch(boardId: String): Board? {
        return jooq.selectFrom(Tables.BOARDS)
            .where(Tables.BOARDS.BOARD_ID.eq(boardId))
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun insert(board: Board) {
        try {
            jooq.insertInto(Tables.BOARDS)
                .set(Tables.BOARDS.BOARD_ID, board.boardId)
                .set(Tables.BOARDS.NAME, board.name)
                .set(Tables.BOARDS.CREATED_DATETIME, board.createdDatetime)
                .set(Tables.BOARDS.ACCOUNT_ID, board.accountId)
                .execute()
        } catch (e: Exception) {
            throw DomainException(DomainErrorCodes.AccountRegister.duplicate, "Duplicated key")
        }
    }

    override fun update(board: Board) {
        jooq.update(Tables.BOARDS)
            .set(Tables.BOARDS.BOARD_ID, board.boardId)
            .set(Tables.BOARDS.NAME, board.name)
            .set(Tables.BOARDS.CREATED_DATETIME, board.createdDatetime)
            .set(Tables.BOARDS.ACCOUNT_ID, board.accountId)
            .where(Tables.BOARDS.BOARD_ID.eq(board.boardId))
            .execute()
    }

    override fun delete(boardId: String) {
        jooq.deleteFrom(Tables.BOARDS)
            .where(Tables.BOARDS.BOARD_ID.eq(boardId))
            .execute()
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
