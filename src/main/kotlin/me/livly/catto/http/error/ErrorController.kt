package me.livly.catto.http.error

import me.livly.catto.http.response.APIErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ErrorController {
    @ExceptionHandler
    fun handleAPIError(ex: APIError): ResponseEntity<APIErrorResponse> {
        val resp = APIErrorResponse(
            statusCode = ex.statusCode,
            message = ex.message ?: "internal server error",
            internalCode = ex.internalCode
        )
        return ResponseEntity(resp, ex.statusCode)
    }
}
