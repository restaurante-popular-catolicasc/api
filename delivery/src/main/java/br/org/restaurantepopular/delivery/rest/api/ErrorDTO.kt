package br.org.restaurantepopular.delivery.rest.api

data class ErrorDTO(
    val errorCode: ErrorCode?,
    val message: String?
)

enum class ErrorCode {
    NOT_FOUND,
    VALIDATION_ERROR
}