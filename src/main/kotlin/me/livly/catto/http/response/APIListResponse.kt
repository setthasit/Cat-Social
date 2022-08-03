package me.livly.catto.http.response

import org.springframework.http.HttpStatus

data class APIListResponse<T>(
    val statusCode: HttpStatus,
    val items: List<T>
)