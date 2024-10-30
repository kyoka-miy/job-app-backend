package com.example.job_app.presentation.controller

import com.example.job_app.usecase.place.GetPlaceSuggestionsUseCase
import com.example.job_app.usecase.place.PlaceSuggestionDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/places")
class PlaceController(
    private val getPlaceSuggestionsUseCase: GetPlaceSuggestionsUseCase
) {
    @GetMapping
    fun getPlaceSuggestions(@RequestParam input: String): List<PlaceSuggestionDto> {
        return getPlaceSuggestionsUseCase.execute(input)
    }
}
