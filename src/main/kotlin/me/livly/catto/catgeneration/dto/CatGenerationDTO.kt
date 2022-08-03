package me.livly.catto.catgeneration.dto

import me.livly.catto.cat.Cat
import me.livly.catto.cat.dto.CatWithoutGenerationDTO
import me.livly.catto.catgeneration.CatGeneration

data class CatGenerationDTO(
    val id: Long,
    var generation: Long,
    var description: String? = null,
    var fatherCat: CatWithoutGenerationDTO? = null,
    var motherCat: CatWithoutGenerationDTO? = null,
) {
    companion object {
        fun fromCatGenerationEntity(generation: CatGeneration): CatGenerationDTO {
            return CatGenerationDTO(
                id = generation.id,
                generation = generation.generation,
                description = generation.description,
                fatherCat = generation.fatherCat?.let { CatWithoutGenerationDTO.formCatEntity(it) },
                motherCat = generation.motherCat?.let { CatWithoutGenerationDTO.formCatEntity(it) },
            )
        }
    }
}

