package me.livly.catto.cat.exception

import me.livly.catto.http.error.APIError
import org.springframework.http.HttpStatus

class CatAlreadyExisted : APIError("cat is already existed", HttpStatus.BAD_REQUEST)
