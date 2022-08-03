package me.livly.catto.cat.implementer

import me.livly.catto.cat.Cat
import me.livly.catto.cat.CatRepository
import me.livly.catto.cat.CatUsecase
import me.livly.catto.cat.exception.CatNotExisted
import me.livly.catto.catgeneration.CatGeneration
import me.livly.catto.catgeneration.CatGenerationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CatUsecaseImpl(
    val catRepository: CatRepository,
    val catGenerationRepository: CatGenerationRepository,
) : CatUsecase {
    override fun findAllCats(): List<Cat> {
        return catRepository.findAll()
    }

    override fun findAllCatByGenerationID(generationID: Long): List<Cat> {
        return catRepository.findAllByGenerationId(generationID)
    }

    override fun findCatByID(id: Long): Cat {
        val cat = catRepository.findById(id)
        if (cat.isEmpty) {
            throw CatNotExisted()
        }
        return cat.get()
    }

    override fun createNewCat(cat: Cat): Cat {
        val catGeneration = CatGeneration(
            id = 0,
            generation = 0,
            description = "genesis",
            createdAt = Date(),
            updatedAt = Date()
        )
        cat.generation = catGenerationRepository.saveAndFlush(catGeneration)
        return catRepository.saveAndFlush(cat)
    }

    override fun createNewCatWithGeneration(
        generation: CatGeneration,
        cats: List<Cat>
    ): Pair<CatGeneration, List<Cat>> {
        val newGeneration = catGenerationRepository.saveAndFlush(generation)
        cats.forEach { cat: Cat ->
            cat.generation = newGeneration
        }

        return newGeneration to catRepository.saveAllAndFlush(cats)
    }

    override fun updateCat(id: Long, cat: Cat): Cat {
        val result = catRepository.findById(id)
        if (result.isEmpty) {
            throw CatNotExisted()
        }

        cat.id = id
        return catRepository.saveAndFlush(cat)
    }

    override fun deleteCat(id: Long) {
        if (!catRepository.existsById(id)) {
            throw CatNotExisted()
        }
        catRepository.deleteById(id)
    }
}
