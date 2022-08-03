package me.livly.catto.http.error

import org.springframework.http.HttpStatus

open class APIError(
    message: String,
    val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val internalCode: String = "CAT-000000"
) : RuntimeException(message)