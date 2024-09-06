package com.example.job_app.presentation.shared

import com.example.job_app.domain.shared.DomainException
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler // for validation error
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse("invalidRequest", ex.bindingResult.allErrors[0].defaultMessage ?: "Invalid value")
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleUseCaseException(ex: UseCaseException): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse(ex.errorCode.value, ex.message)
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleDomainException(ex: DomainException): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse(ex.errorCode.value, ex.message)
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST)
    }
}
