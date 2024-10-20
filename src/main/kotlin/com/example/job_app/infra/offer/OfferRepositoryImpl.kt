package com.example.job_app.infra.offer

import com.example.job_app.domain.offer.Offer
import com.example.job_app.domain.offer.OfferRepository
import com.example.job_app.infra.jooq.Tables
import com.example.job_app.infra.jooq.tables.records.OffersRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class OfferRepositoryImpl(
    private val jooq: DSLContext
) : OfferRepository {
    override fun fetchByBoardId(boardId: String): List<Offer> {
        return jooq.selectFrom(Tables.OFFERS)
            .where(Tables.OFFERS.BOARD_ID.eq(boardId))
            .and(Tables.OFFERS.ACCEPTED_DATETIME.isNull)
            .orderBy(Tables.OFFERS.DEADLINE_DATETIME.asc())
            .fetch()
            .mapNotNull {
                recordToEntity(it)
            }
    }

    override fun insert(offer: Offer) {
        jooq.insertInto(Tables.OFFERS)
            .set(Tables.OFFERS.OFFER_ID, offer.offerId)
            .set(Tables.OFFERS.RECEIVED_DATETIME, offer.receivedDateTime)
            .set(Tables.OFFERS.DEADLINE_DATETIME, offer.deadlineDateTime)
            .set(Tables.OFFERS.ACCEPTED_DATETIME, offer.acceptedDateTime)
            .set(Tables.OFFERS.JOB_ID, offer.jobId)
            .set(Tables.OFFERS.ACTIVITY_ID, offer.activityId)
            .set(Tables.OFFERS.BOARD_ID, offer.boardId)
            .execute()
    }

    override fun fetch(offerId: String): Offer? {
        return jooq.selectFrom(Tables.OFFERS)
            .where(Tables.OFFERS.OFFER_ID.eq(offerId))
            .fetchOne()
            ?.let {
                recordToEntity(it)
            }
    }

    override fun delete(offerId: String) {
        jooq.deleteFrom(Tables.OFFERS)
            .where(Tables.OFFERS.OFFER_ID.eq(offerId))
            .execute()
    }

    override fun update(offer: Offer) {
        jooq.update(Tables.OFFERS)
            .set(Tables.OFFERS.RECEIVED_DATETIME, offer.receivedDateTime)
            .set(Tables.OFFERS.DEADLINE_DATETIME, offer.deadlineDateTime)
            .set(Tables.OFFERS.ACCEPTED_DATETIME, offer.acceptedDateTime)
            .execute()
    }

    private fun recordToEntity(record: OffersRecord): Offer {
        return Offer(
            offerId = record.offerId,
            receivedDateTime = record.receivedDatetime,
            deadlineDateTime = record.deadlineDatetime,
            acceptedDateTime = record.acceptedDatetime,
            jobId = record.jobId,
            activityId = record.activityId,
            boardId = record.boardId
        )
    }
}
