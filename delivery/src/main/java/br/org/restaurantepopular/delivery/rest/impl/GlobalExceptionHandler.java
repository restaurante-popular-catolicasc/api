package br.org.restaurantepopular.delivery.rest.impl;

import br.org.restaurantepopular.delivery.rest.api.ErrorDTO;
import br.org.restaurantepopular.usecase.core.exception.NotFoundException;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> notFound(NotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorDTO(ErrorDTO.ErrorCode.NOT_FOUND, ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> unprocessableEntity(ValidationException ex) {
        return new ResponseEntity<>(
                new ErrorDTO(ErrorDTO.ErrorCode.VALIDATION_ERROR, ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
