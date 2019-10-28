package br.org.restaurantepopular.delivery.rest.impl

import br.org.restaurantepopular.delivery.rest.api.ErrorCode
import br.org.restaurantepopular.delivery.rest.api.ErrorDTO
import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import br.org.restaurantepopular.usecase.core.exception.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException) =
        ResponseEntity(ErrorDTO(ErrorCode.NOT_FOUND, "Resource not found"), HttpStatus.NOT_FOUND)

    @ExceptionHandler(ValidationException::class)
    fun validationError(ex: ValidationException) =
        ResponseEntity(ErrorDTO(ErrorCode.VALIDATION_ERROR, ex.message), HttpStatus.UNPROCESSABLE_ENTITY)
}