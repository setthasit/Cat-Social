package me.livly.catto.cat

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import me.livly.catto.cat.exception.CatNotExisted
import me.livly.catto.cat.implementer.CatUsecaseImpl
import me.livly.catto.catgeneration.CatGeneration
import me.livly.catto.catgeneration.CatGenerationRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

val Cat1 = Cat.fromID(1)
val Cat2 = Cat.fromID(2)
val Cat3 = Cat.fromID(3)
val catGeneration = CatGeneration(
    id = 0,
    generation = 0,
    description = "genesis",
    createdAt = Date(),
    updatedAt = Date()
)

@ExtendWith(MockKExtension::class)
class CatUsecaseTest(
) {
    private val catRepository: CatRepository = mockk()
    private val catGenerationRepository: CatGenerationRepository = mockk()
    private val catUsecase: CatUsecase = CatUsecaseImpl(catRepository, catGenerationRepository)

    @Test
    fun `Test find all cats`() {
        val expectedCats = listOf(Cat1, Cat2, Cat3)
        every { catRepository.findAll() } returns expectedCats
        val cats = catUsecase.findAllCats()

        verify { catRepository.findAll() }
        confirmVerified(catRepository)

        assertEquals(cats, expectedCats)
    }

    @Test
    fun `Test find cat by ID(founded)`() {
        val catID: Long = 1
        every { catRepository.findById(catID) } returns Optional.of(Cat1)
        val cat = catUsecase.findCatByID(catID)

        verify { catRepository.findById(catID) }
        confirmVerified(catRepository)

        assertEquals(cat, Cat1)
    }

    @Test
    fun `Test find cat by ID(not found)`() {
        val catID: Long = 1
        var exceptionThrow = false
        every { catRepository.findById(catID) } returns Optional.empty()

        try {
            catUsecase.findCatByID(catID)
        } catch (e: CatNotExisted) {
            exceptionThrow = true
        }
        verify { catRepository.findById(catID) }
        confirmVerified(catRepository)

        assertTrue(exceptionThrow)
    }

    @Test
    fun `Test create new cat`() {
        val expectedCatGeneration = catGeneration
        expectedCatGeneration.id = 1
        val expectedCat = Cat1
        expectedCat.id = 1
        expectedCat.generation = expectedCatGeneration

        every { catGenerationRepository.saveAndFlush(any()) } answers { expectedCatGeneration }
        every { catRepository.saveAndFlush(any()) } answers { expectedCat }

        val cat = catUsecase.createNewCat(Cat1)

        verify {
            catGenerationRepository.saveAndFlush(any())
            catRepository.saveAndFlush(Cat1)
        }
        confirmVerified(catGenerationRepository, catRepository)

        assertEquals(cat, expectedCat)
        assertEquals(cat.generation, expectedCatGeneration)
    }

    @Test
    fun `Test create new cat with generation`() {
        // Generation
        val expectedCatGeneration = catGeneration
        expectedCatGeneration.id = 1
        // cats
        val requestCats = listOf(Cat1, Cat2, Cat3)
        val expectedCats = requestCats.map {
            it.generation.id = 1
            it
        }

        every { catGenerationRepository.saveAndFlush(catGeneration) } answers { expectedCatGeneration }
        every { catRepository.saveAllAndFlush(requestCats) } answers { expectedCats }

        val (generation, cats) = catUsecase.createNewCatWithGeneration(catGeneration, requestCats)

        verify {
            catGenerationRepository.saveAndFlush(catGeneration)
            catRepository.saveAllAndFlush(requestCats)
        }
        confirmVerified(catGenerationRepository, catRepository)

        assertEquals(cats, expectedCats)
        assertEquals(generation, expectedCatGeneration)
    }

}
