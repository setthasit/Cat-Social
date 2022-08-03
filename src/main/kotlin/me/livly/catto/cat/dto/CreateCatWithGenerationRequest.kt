package me.livly.catto.cat.dto

import me.livly.catto.cat.Cat
import me.livly.catto.catgeneration.dto.CreateCatGenerationRequest
import me.livly.catto.catgeneration.dto.toCatGenerationEntity

data class CreateCatWithGenerationRequest(
    val generation: CreateCatGenerationRequest,
    val cats: List<CreateCatRequest>
)

fun CreateCatWithGenerationRequest.toCatEntity(): List<Cat> {
    return cats.map { it.toCatEntity() }
}

fun CreateCatWithGenerationRequest.toCatGenerationEntity() = generation.toCatGenerationEntity()
