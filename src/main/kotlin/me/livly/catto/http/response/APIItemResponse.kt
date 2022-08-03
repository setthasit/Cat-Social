package me.livly.catto.http.response

import org.springframework.http.HttpStatus

data class APIItemResponse<T>(
    val statusCode: HttpStatus,
    val item: T
)