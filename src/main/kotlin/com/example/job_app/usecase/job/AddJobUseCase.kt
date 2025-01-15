package com.example.job_app.usecase.job

import com.example.job_app.domain.activity.Activity
import com.example.job_app.domain.activity.ActivityRepository
import com.example.job_app.domain.board.BoardRepository
import com.example.job_app.domain.job.Job
import com.example.job_app.domain.job.JobRepository
import com.example.job_app.domain.job.Status
import com.example.job_app.domain.job.WorkStyle
import com.example.job_app.infra.place.PlaceProperties
import com.example.job_app.usecase.place.PlaceSuggestionDto
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class AddJobUseCase(
    private val jobRepository: JobRepository,
    private val boardRepository: BoardRepository,
    private val activityRepository: ActivityRepository,
    private val placeProperties: PlaceProperties,
    private val restTemplate: RestTemplate
) {
    fun execute(
        boardId: String?,
        jobTitle: String,
        companyName: String,
        url: String?,
        location: String?,
        placeId: String?,
        salary: String?,
        workStyle: WorkStyle?,
        jobBoard: String?,
        note: String?,
        status: Status,
        appliedDate: LocalDate?
    ) {
        if (boardId == null || boardRepository.fetch(boardId) == null) {
            throw UseCaseException(UseCaseErrorCodes.Common.idNotFound, "BoardId not found")
        }
        var latitude: BigDecimal? = null
        var longitude: BigDecimal? = null
        if (location != null && placeId != null) {
            val requestUrl = "${placeProperties.url}/details/json?place_id=$placeId&key=${placeProperties.apiKey}"
            try {
                val response: ResponseEntity<Map<String, Any>> = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<Map<String, Any>>() {}
                )
                val result = response.body?.get("result") as? Map<*, *>
                val geometry = result?.get("geometry") as? Map<*, *>
                val loc = geometry?.get("location") as? Map<*, *>
                latitude = (loc?.get("lat") as? Double)?.toBigDecimal()
                longitude = (loc?.get("lng") as? Double)?.toBigDecimal()
            } catch (e: HttpStatusCodeException) {
                // Don't stop job update process
                when (e.statusCode.value()) {
                    400 -> println("Bad request format for place api")
                    401 -> println("Token is not valid")
                    403 -> println("Access is restricted")
                    404 -> println("Cannot find the resource")
                    500 -> println("Server error")
                    else -> println("Failed in the request for place api")
                }
            }
        }
        val job = Job(
            boardId = boardId,
            jobTitle = jobTitle,
            companyName = companyName,
            url = url,
            location = location,
            placeId = placeId,
            latitude = latitude,
            longitude = longitude,
            salary = salary,
            workStyle = workStyle,
            status = status,
            appliedDate = appliedDate,
            jobBoard = jobBoard,
            note = note,
            addedDatetime = LocalDateTime.now()
        )
        jobRepository.insert(job)
        if (appliedDate != null) {
            val activity = Activity(
                name = "Applied",
                activityDateTime = appliedDate.atStartOfDay(),
                deleted = false,
                jobId = job.jobId
            )
            activityRepository.insert(activity)
        }
    }
}
