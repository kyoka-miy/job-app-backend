package com.example.job_app.usecase.offer

import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.offer.Offer
import com.example.job_app.domain.offer.OfferRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class GetUpcomingOffersUseCase(
    private val boardRepository: BoardRepository,
    private val offerRepository: OfferRepository
) {
    fun execute(boardId: String): List<Offer> {
        boardRepository.fetch(boardId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Board not found")
        return offerRepository.fetchByBoardId(boardId)
    }
}
