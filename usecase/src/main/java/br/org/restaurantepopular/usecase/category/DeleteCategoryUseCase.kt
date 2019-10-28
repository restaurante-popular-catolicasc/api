package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.usecase.core.IUseCase

class DeleteCategoryUseCase(
    private val categoryRepository: ICategoryRepository
) : IUseCase<String, Unit> {

    override fun execute(request: String) {
        categoryRepository.delete(request)
    }
}