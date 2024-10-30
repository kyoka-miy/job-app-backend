package com.example.job_app.usecase.place

import com.example.job_app.infra.place.PlaceProperties
import com.example.job_app.usecase.shared.UseCaseErrorCodes
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate

@Component
class GetPlaceSuggestionsUseCase(
    private val placeProperties: PlaceProperties,
    private val restTemplate: RestTemplate
) {
    fun execute(input: String): List<PlaceSuggestionDto> {
        val requestUrl = "${placeProperties.url}?input=$input&types=(cities)&key=${placeProperties.apiKey}"

        try {
            val response: ResponseEntity<Map<String, Any>> = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Map<String, Any>>() {}
            )
            val predictions = response.body?.get("predictions") as List<Map<String, Any>>
            return predictions.map {
                PlaceSuggestionDto(
                    it["description"] as String,
                    it["place_id"] as String
                )
            }
        } catch (e: HttpStatusCodeException) {
            when (e.statusCode.value()) {
                400 -> throw UseCaseException(UseCaseErrorCodes.Place.badRequest, "Bad request format for place api")
                401 -> throw UseCaseException(UseCaseErrorCodes.Place.unauthorized, "Token is not valid")
                403 -> throw UseCaseException(UseCaseErrorCodes.Place.forbidden, "Access is restricted")
                404 -> throw UseCaseException(UseCaseErrorCodes.Place.resourceNotFound, "Cannot find the resource")
                500 -> throw UseCaseException(UseCaseErrorCodes.Place.internalServerError, "Server error")
                else -> throw UseCaseException(UseCaseErrorCodes.Place.other, "Failed in the request for place api")
            }
        }
    }
}

data class PlaceSuggestionDto(
    val description: String,
    val placeId: String
)
