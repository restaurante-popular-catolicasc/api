package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.IUseCase
import br.org.restaurantepopular.usecase.core.exception.ValidationException

class UpdateProductTypeUseCase(
    private val productTypeRepository: IProductTypeRepository
) : IUseCase<ProductType, Unit> {

    override fun execute(request: ProductType) {
        if (request.id.isNullOrBlank())
            throw ValidationException("Product type id should not be empty")
        if (request.name.isBlank())
            throw ValidationException("Product type name should not be empty")

        productTypeRepository.update(request)
    }
}
