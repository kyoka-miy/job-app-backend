package com.example.job_app.presentation.shared

import com.example.job_app.domain.shared.DomainException
import com.example.job_app.usecase.shared.UseCaseException
import org.springframework.beans.BeanInstantiationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.io.PrintWriter
import java.io.StringWriter

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler // for validation error
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse("invalidRequest", ex.bindingResult.allErrors[0].defaultMessage ?: "Invalid value")
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler // for other validation error (ex. when there is a null field)
    fun handleHttpMessageConversionException(
        exception: RuntimeException
    ): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse("invalidRequest", "Request format error")
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

    @ExceptionHandler(Exception::class)
    fun handleThrowable(exception: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val error = ExceptionResponse(errorCode = "500", message = "Server error")
        return ResponseEntity<ExceptionResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
