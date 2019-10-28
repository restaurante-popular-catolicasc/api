package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.IUseCase

class ListProductTypesUseCase(
    private val productTypeRepository: IProductTypeRepository
) : IUseCase<Unit, List<ProductType>> {

    override fun execute(request:Unit): List<ProductType> {
        return productTypeRepository.listProductTypes()
    }
}