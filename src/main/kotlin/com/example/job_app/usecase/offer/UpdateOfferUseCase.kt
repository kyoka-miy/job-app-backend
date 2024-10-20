package com.example.job_app.usecase.offer

import com.example.job_app.domain.offer.OfferRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UpdateOfferUseCase(
    private val offerRepository: OfferRepository
) {
    fun execute(
        offerId: String,
        receivedDateTime: LocalDateTime,
        deadlineDateTime: LocalDateTime,
        acceptedDateTime: LocalDateTime?
    ) {
        val offer = offerRepository.fetch(offerId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Offer not found")
        if (!(receivedDateTime<deadlineDateTime && deadlineDateTime < acceptedDateTime)) throw UseCaseException(UseCaseErrorCodes.Offer.invalidDateTime, "Invalid DateTime")
        offer.receivedDateTime = receivedDateTime
        offer.deadlineDateTime = deadlineDateTime
        offer.acceptedDateTime = acceptedDateTime
        offerRepository.update(offer)
    }
}
