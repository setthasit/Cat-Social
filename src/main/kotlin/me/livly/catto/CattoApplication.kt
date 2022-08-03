package me.livly.catto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CattoApplication

fun main(args: Array<String>) {
    runApplication<CattoApplication>(*args)
}
