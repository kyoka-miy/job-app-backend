package com.example.job_app.usecase.offer

import com.example.job_app.domain.offer.OfferRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.stereotype.Component

@Component
class DeleteOfferUseCase(
    private val offerRepository: OfferRepository
) {
    fun execute(offerId: String) {
        offerRepository.fetch(offerId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Offer not found")
        offerRepository.delete(offerId)
    }
}
