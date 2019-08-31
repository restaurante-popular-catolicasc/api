package br.org.restaurantepopular.delivery.rest.api;

import org.springframework.lang.Nullable;

public class ErrorDTO {

    private ErrorCode errorCode;
    private String message;

    public ErrorDTO(@Nullable ErrorCode errorCode, @Nullable String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public enum ErrorCode {
        NOT_FOUND,
        VALIDATION_ERROR
    }
}
