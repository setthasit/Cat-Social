package me.livly.catto.cat.dto

import me.livly.catto.cat.Cat
import me.livly.catto.catgeneration.CatGeneration
import me.livly.catto.catgeneration.dto.CatGenerationDTO
import me.livly.catto.http.exception.InvalidResponse

data class CreateCatWithGenerationResponse(
    val generation: CatGenerationDTO,
    val cats: List<CatWithoutGenerationDTO>
) {
    companion object {
        fun fromEntity(generation: CatGeneration, cats: List<Cat>): CreateCatWithGenerationResponse {
            if (cats.isEmpty()) {
                throw InvalidResponse()
            }
            return CreateCatWithGenerationResponse(
                generation = CatGenerationDTO.fromCatGenerationEntity(generation),
                cats = cats.map { cat: Cat ->
                    CatWithoutGenerationDTO.formCatEntity(cat)
                }
            )
        }
    }
}