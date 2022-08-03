package me.livly.catto.catgeneration

import org.springframework.data.jpa.repository.JpaRepository

interface CatGenerationRepository : JpaRepository<CatGeneration, Long>