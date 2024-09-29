package com.example.job_app.usecase.offer

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.offer.Offer
import com.example.job_app.domain.offer.OfferRepository
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Transactional
class AddOfferUseCase(
    private val offerRepository: OfferRepository,
    private val jobRepository: JobRepository,
    private val activityRepository: ActivityRepository
) {
    fun execute(
        jobId: String,
        receivedDateTime: LocalDateTime,
        deadlineDateTime: LocalDateTime,
        acceptedDateTime: LocalDateTime?
    ) {
        val job = jobRepository.fetch(jobId) ?: throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "Job not found")
        if (!(receivedDateTime<deadlineDateTime && deadlineDateTime < acceptedDateTime)) throw UseCaseException(UseCaseErrorCodes.Offer.invalidDateTime, "Invalid DateTime")
        val activity = Activity(
            name = "Offer Received",
            activityDateTime = receivedDateTime,
            deleted = false,
            jobId = jobId
        )
        activityRepository.insert(activity)
        val offer = Offer(
            receivedDateTime = receivedDateTime,
            deadlineDateTime = deadlineDateTime,
            acceptedDateTime = acceptedDateTime,
            jobId = jobId,
            activityId = activity.activityId,
            boardId = job.boardId
        )
        offerRepository.insert(offer)
    }
}
