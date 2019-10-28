package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.IUseCase
import br.org.restaurantepopular.usecase.core.exception.ValidationException

class CreateProductTypeUseCase(
    private val productTypeRepository: IProductTypeRepository
) : IUseCase<ProductType, ProductType> {

    override fun execute(request: ProductType): ProductType {
        if (request.name.isBlank())
            throw ValidationException("Product type name should not be empty")

        return productTypeRepository.save(request)
    }
}
