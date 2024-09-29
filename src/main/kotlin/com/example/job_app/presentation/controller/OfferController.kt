package com.example.job_app.presentation.controller

import com.example.job_app.domain.offer.Offer
import com.example.job_app.usecase.offer.AddOfferUseCase
import com.example.job_app.usecase.offer.DeleteOfferUseCase
import com.example.job_app.usecase.offer.GetUpcomingOffersUseCase
import com.example.job_app.usecase.offer.UpdateOfferUseCase
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/offers")
class OfferController(
    private val getUpcomingOffersUseCase: GetUpcomingOffersUseCase,
    private val addOfferUseCase: AddOfferUseCase,
    private val deleteOfferUseCase: DeleteOfferUseCase,
    private val updateOfferUseCase: UpdateOfferUseCase
) {
    @GetMapping("/{boardId}")
    fun getUpcomingOffers(@PathVariable("boardId") boardId: String): List<Offer> {
        return getUpcomingOffersUseCase.execute(boardId)
    }

    @PostMapping("/{jobId}")
    fun addOfferAndActivity(
        @PathVariable("jobId") jobId: String,
        @RequestBody @Validated
        request: AddOrUpdateOfferRequest
    ) {
        addOfferUseCase.execute(
            jobId,
            request.receivedDateTime,
            request.deadlineDateTime,
            request.acceptedDateTime
        )
    }

    @DeleteMapping("/{offerId}")
    fun deleteOffer(@PathVariable("offerId") offerId: String) {
        deleteOfferUseCase.execute(offerId)
    }

    @PutMapping("/{offerId}")
    fun updateOffer(
        @PathVariable("offerId") offerId: String,
        @RequestBody @Validated
        request: AddOrUpdateOfferRequest
    ) {
        updateOfferUseCase.execute(offerId, request.receivedDateTime, request.deadlineDateTime, request.acceptedDateTime)
    }
}

data class AddOrUpdateOfferRequest(
    val receivedDateTime: LocalDateTime,
    val deadlineDateTime: LocalDateTime,
    val acceptedDateTime: LocalDateTime?
)
