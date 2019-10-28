package br.org.restaurantepopular.delivery.rest.api.product_type

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/productType")
interface IProductTypeResource {

    @GetMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getProductTypeById(@PathVariable("id") id: String): CompletionStage<ProductTypeDTO>

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createProductType(@RequestBody productTypeDTO: ProductTypeDTO): CompletionStage<ResponseEntity<ProductTypeDTO>>

    @PutMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateProductType(
        @PathVariable("id") id: String,
        @RequestBody productTypeDTO: ProductTypeDTO
    ): CompletionStage<ResponseEntity<Unit>>

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun listProductTypes(): CompletionStage<List<ProductTypeDTO>>

    @DeleteMapping(value = ["/{id}"])
    fun deleteProductType(@PathVariable("id") id: String): CompletionStage<ResponseEntity<Unit>>
}