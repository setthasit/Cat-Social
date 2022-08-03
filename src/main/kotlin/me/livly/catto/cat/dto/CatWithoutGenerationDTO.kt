package me.livly.catto.cat.dto

import me.livly.catto.cat.Cat

data class CatWithoutGenerationDTO(
    val id: Long,
    val name: String,
    val age: UInt,
    val description: String? = null,
) {
    companion object {
        fun formCatEntity(cat: Cat): CatWithoutGenerationDTO {
            return CatWithoutGenerationDTO(
                id = cat.id,
                name = cat.name,
                age = cat.age,
                description = cat.description,
            )
        }

        fun formCatEntityList(cats: List<Cat>): List<CatWithoutGenerationDTO> {
            return cats.map { cat: Cat ->
                formCatEntity(cat)
            }
        }
    }
}

