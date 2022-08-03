package me.livly.catto.cat

import me.livly.catto.catgeneration.CatGeneration

interface CatUsecase {
    fun findAllCats(): List<Cat>
    fun findAllCatByGenerationID(generationID: Long): List<Cat>
    fun findCatByID(id: Long): Cat
    fun createNewCat(cat: Cat): Cat
    fun createNewCatWithGeneration(generation: CatGeneration, cats: List<Cat>): Pair<CatGeneration, List<Cat>>
    fun updateCat(id: Long, cat: Cat): Cat
    fun deleteCat(id: Long)
}