package me.livly.catto.cat

import org.springframework.data.jpa.repository.JpaRepository

interface CatRepository : JpaRepository<Cat, Long> {
    fun findAllByGenerationId(generationID: Long): List<Cat>
}