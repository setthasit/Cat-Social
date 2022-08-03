package me.livly.catto.cat.dto

import me.livly.catto.cat.Cat
import me.livly.catto.catgeneration.dto.CatGenerationDTO

data class CatDTO(
    val id: Long,
    val name: String,
    val age: UInt,
    val description: String? = null,
    val generation: CatGenerationDTO,
) {
    companion object {
        fun formCatEntity(cat: Cat): CatDTO {
            return CatDTO(
                id = cat.id,
                name = cat.name,
                age = cat.age,
                description = cat.description,
                generation = CatGenerationDTO.fromCatGenerationEntity(cat.generation),
            )
        }

        fun formCatEntityList(cats: List<Cat>): List<CatDTO> {
            return cats.map { cat: Cat ->
                formCatEntity(cat)
            }
        }
    }
}

