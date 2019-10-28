package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.core.IUseCase

class GetCategoryByIdUseCase(
        private val categoryRepository: ICategoryRepository
) : IUseCase<String, Category> {

    override fun execute(request: String): Category {
        return categoryRepository.findById(request)
    }
}