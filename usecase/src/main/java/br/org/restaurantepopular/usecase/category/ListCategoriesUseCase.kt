package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.core.IUseCase

class ListCategoriesUseCase(
    private val categoryRepository: ICategoryRepository
) : IUseCase<Unit, List<Category>> {

    override fun execute(request: Unit): List<Category> {
        return categoryRepository.listCategories()
    }
}