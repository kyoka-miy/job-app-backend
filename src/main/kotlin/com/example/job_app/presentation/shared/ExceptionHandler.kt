package com.example.job_app.presentation.shared

import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler // for validation error
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = ex.bindingResult.allErrors.associate { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: "Invalid value"
            fieldName to errorMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleException(ex: UseCaseException): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse(ex.errorCode, ex.message)
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST)
    }
}
