package br.org.restaurantepopular.delivery.rest.impl

import br.org.restaurantepopular.delivery.rest.api.product_type.IProductTypeResource
import br.org.restaurantepopular.delivery.rest.api.product_type.ProductTypeDTO
import br.org.restaurantepopular.delivery.rest.api.product_type.toProductType
import br.org.restaurantepopular.delivery.rest.api.product_type.toProductTypeDTO
import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor
import br.org.restaurantepopular.usecase.product_type.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductTypeResource @Autowired
constructor(
    private val useCaseExecutor: IUseCaseExecutor,
    private val getProductTypeByIdUseCase: GetProductTypeByIdUseCase,
    private val createProductTypeUseCase: CreateProductTypeUseCase,
    private val updateProductTypeUseCase: UpdateProductTypeUseCase,
    private val listProductTypesUseCase: ListProductTypesUseCase,
    private val deleteProductTypeUseCase: DeleteProductTypeUseCase
) : IProductTypeResource {

    override fun getProductTypeById(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = getProductTypeByIdUseCase,
        requestDto = id,
        requestConverter = { it },
        responseConverter = { it.toProductTypeDTO() }
    )

    override fun createProductType(
        @RequestBody productTypeDTO: ProductTypeDTO
    ) = useCaseExecutor(
        useCase = createProductTypeUseCase,
        requestDto = productTypeDTO,
        requestConverter = ProductTypeDTO::toProductType,
        responseConverter = { ResponseEntity(it.toProductTypeDTO(), HttpStatus.CREATED) }
    )

    override fun updateProductType(
        @PathVariable("id") id: String,
        @RequestBody productTypeDTO: ProductTypeDTO
    ) = useCaseExecutor(
        useCase = updateProductTypeUseCase,
        requestDto = productTypeDTO.apply { this.id = id },
        requestConverter = ProductTypeDTO::toProductType,
        responseConverter = { ResponseEntity<Unit>(HttpStatus.OK) }
    )

    override fun listProductTypes() = useCaseExecutor(
        useCase = listProductTypesUseCase,
        responseConverter = { it.map(ProductType::toProductTypeDTO) })

    override fun deleteProductType(
        @PathVariable("id") id: String
    ) = useCaseExecutor(
        useCase = deleteProductTypeUseCase,
        requestDto = id,
        requestConverter = { it },
        responseConverter = { ResponseEntity<Unit>(HttpStatus.NO_CONTENT) }
    )
}