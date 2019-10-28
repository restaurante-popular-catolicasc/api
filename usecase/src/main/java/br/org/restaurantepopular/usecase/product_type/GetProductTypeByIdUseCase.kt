package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.IUseCase

class GetProductTypeByIdUseCase(
    private val categoryRepository: IProductTypeRepository
) : IUseCase<String, ProductType> {

    override fun execute(request: String): ProductType {
        return categoryRepository.findById(request)
    }
}
