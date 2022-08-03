package me.livly.catto.cat.dto

import me.livly.catto.cat.Cat
import me.livly.catto.catgeneration.CatGeneration
import org.jetbrains.annotations.NotNull
import java.util.*

data class CreateCatRequest(
    @NotNull
    val name: String,
    @NotNull
    val age: UInt,
    val description: String? = null,
)

fun CreateCatRequest.toCatEntity(): Cat {
    val now = Date()
    return Cat(
        id = 0,
        name = name,
        age = age,
        description = description,
        generation = CatGeneration(
            id = 0,
            description = description,
            generation = 0,
            createdAt = now,
            updatedAt = now
        ),
        createdAt = now,
        updatedAt = now
    )
}
