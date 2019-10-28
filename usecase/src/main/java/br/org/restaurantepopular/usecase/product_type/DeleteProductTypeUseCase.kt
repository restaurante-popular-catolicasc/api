package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.usecase.core.IUseCase

class DeleteProductTypeUseCase(
    private val productTypeRepository: IProductTypeRepository
) : IUseCase<String, Unit> {

    override fun execute(s: String) {
        productTypeRepository.delete(s)
    }
}
