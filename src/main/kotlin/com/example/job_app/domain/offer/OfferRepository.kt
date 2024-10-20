package com.example.job_app.domain.offer

interface OfferRepository {
    fun fetchByBoardId(boardId: String): List<Offer>
    fun insert(offer: Offer)
    fun fetch(offerId: String): Offer?
    fun delete(offerId: String)
    fun update(offer: Offer)
}
