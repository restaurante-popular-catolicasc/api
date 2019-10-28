package br.org.restaurantepopular.delivery.rest.api.category

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/categories")
interface ICategoryResource {

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getCategoryById(@PathVariable("id") id: String): CompletionStage<CategoryDTO>

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createCategory(@RequestBody categoryDTO: CategoryDTO): CompletionStage<ResponseEntity<CategoryDTO>>

    @PutMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateCategory(
        @PathVariable("id") id: String,
        @RequestBody categoryDTO: CategoryDTO
    ): CompletionStage<ResponseEntity<Unit>>

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun listCategories(): CompletionStage<List<CategoryDTO>>

    @DeleteMapping(value = ["/{id}"])
    fun deleteCategory(@PathVariable("id") id: String): CompletionStage<ResponseEntity<Unit>>
}