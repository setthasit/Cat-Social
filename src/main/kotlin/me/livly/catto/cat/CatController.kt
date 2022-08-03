package me.livly.catto.cat

import me.livly.catto.cat.dto.*
import me.livly.catto.http.response.APIItemResponse
import me.livly.catto.http.response.APIListResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cats")
class CatController(val catUsecase: CatUsecase) {
    @GetMapping
    fun getAllCats(): APIListResponse<CatDTO> {
        val cats = catUsecase.findAllCats()
        return APIListResponse(
            HttpStatus.OK,
            CatDTO.formCatEntityList(cats),
        )
    }

    @GetMapping("/generation/{generation_id}")
    fun getAllCatsByGeneration(@PathVariable("generation_id") generationID: Long): APIListResponse<CatDTO> {
        val cat = catUsecase.findAllCatByGenerationID(generationID)
        return APIListResponse(
            HttpStatus.OK,
            CatDTO.formCatEntityList(cat),
        )
    }

    @GetMapping("/{id}")
    fun getCatByID(@PathVariable("id") catID: Long): APIItemResponse<CatDTO> {
        val cat = catUsecase.findCatByID(catID)
        return APIItemResponse(
            HttpStatus.OK,
            CatDTO.formCatEntity(cat),
        )
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCat(
        @Validated @RequestBody cat: CreateCatRequest
    ): APIItemResponse<CatDTO> {
        val newCat = catUsecase.createNewCat(cat.toCatEntity())
        return APIItemResponse(
            HttpStatus.CREATED,
            CatDTO.formCatEntity(newCat)
        )
    }

    @PostMapping("/generation")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCatWithGeneration(
        @Validated @RequestBody request: CreateCatWithGenerationRequest
    ): APIItemResponse<CreateCatWithGenerationResponse> {
        val (generation, cats) = catUsecase.createNewCatWithGeneration(
            request.toCatGenerationEntity(),
            request.toCatEntity(),
        )
        return APIItemResponse(
            HttpStatus.CREATED,
            CreateCatWithGenerationResponse.fromEntity(generation, cats)
        )
    }

    @PutMapping("/{id}")
    fun updateCat(
        @PathVariable("id") catID: Long,
        @RequestBody cat: Cat
    ): APIItemResponse<CatDTO> {
        val updatedCat = catUsecase.updateCat(catID, cat)
        return APIItemResponse(
            HttpStatus.CREATED,
            CatDTO.formCatEntity(updatedCat)
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCat(@PathVariable("id") catID: Long) {
        catUsecase.deleteCat(catID)
    }
}
