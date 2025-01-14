package com.example.job_app.infra.board

import com.example.job_app.domain.board.Board
import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.shared.DomainErrorCodes
import com.example.job_app.domain.shared.DomainException
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.BoardsRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class BoardRepositoryImpl(
    private val jooq: DSLContext
) : BoardRepository {
    override fun fetchByAccountId(accountId: String): List<Board> {
        return jooq.selectFrom(Tables.BOARDS)
            .where(Tables.BOARDS.ACCOUNT_ID.eq(accountId))
            .and(Tables.BOARDS.DELETED.isFalse)
            .orderBy(Tables.BOARDS.CREATED_DATETIME.desc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    override fun fetch(boardId: String): Board? {
        return jooq.selectFrom(Tables.BOARDS)
            .where(Tables.BOARDS.BOARD_ID.eq(boardId))
            .and(Tables.BOARDS.DELETED.isFalse)
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
                .set(Tables.BOARDS.DELETED, false)
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
        jooq.update(Tables.BOARDS)
            .set(Tables.BOARDS.DELETED, true)
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
