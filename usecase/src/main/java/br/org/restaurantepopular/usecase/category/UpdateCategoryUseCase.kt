package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.core.IUseCase
import br.org.restaurantepopular.usecase.core.exception.ValidationException

class UpdateCategoryUseCase(
    private val categoryRepository: ICategoryRepository
) : IUseCase<Category, Unit> {

    override fun execute(request: Category) {
        if (request.id.isNullOrEmpty()) {
            throw ValidationException("Category was not found")
        }
        if (request.name.isEmpty())
            throw ValidationException("Category name should not be empty")
        if (request.description.isEmpty())
            throw ValidationException("Category description should not be empty")

        categoryRepository.update(request)
    }
}