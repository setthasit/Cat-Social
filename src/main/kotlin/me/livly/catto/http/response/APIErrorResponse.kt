package me.livly.catto.http.response

import org.springframework.http.HttpStatus

class APIErrorResponse(
    val statusCode: HttpStatus,
    val internalCode: String?,
    val message: String,
)
