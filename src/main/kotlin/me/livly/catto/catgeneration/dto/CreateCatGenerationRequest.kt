package me.livly.catto.catgeneration.dto

import me.livly.catto.cat.Cat
import me.livly.catto.catgeneration.CatGeneration
import org.jetbrains.annotations.NotNull
import java.util.*

data class CreateCatGenerationRequest(
    @NotNull
    var generation: Long,
    var description: String? = null,
    var fatherCatID: Long? = null,
    var motherCatID: Long? = null,
)

fun CreateCatGenerationRequest.toCatGenerationEntity(): CatGeneration {
    val now = Date()
    return CatGeneration(
        id = 0,
        description = description,
        generation = generation,
        fatherCat = fatherCatID?.let { Cat.fromID(it) },
        motherCat = motherCatID?.let { Cat.fromID(it) },
        createdAt = now,
        updatedAt = now
    )
}