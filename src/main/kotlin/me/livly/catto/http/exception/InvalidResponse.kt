package me.livly.catto.http.exception

import me.livly.catto.http.error.APIError
import org.springframework.http.HttpStatus

class InvalidResponse : APIError("invalid response", HttpStatus.INTERNAL_SERVER_ERROR)