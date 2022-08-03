package me.livly.catto.cat.exception

import me.livly.catto.http.error.APIError
import org.springframework.http.HttpStatus

class CatNotFound : APIError("cat not found", HttpStatus.NOT_FOUND)