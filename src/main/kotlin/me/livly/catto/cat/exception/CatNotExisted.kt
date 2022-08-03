package me.livly.catto.cat.exception

import me.livly.catto.http.error.APIError
import org.springframework.http.HttpStatus

class CatNotExisted : APIError("cat is not existed", HttpStatus.BAD_REQUEST)