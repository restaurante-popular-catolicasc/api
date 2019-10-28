package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.core.IUseCase
import br.org.restaurantepopular.usecase.core.exception.ValidationException

class CreateCategoryUseCase(
    private val categoryRepository: ICategoryRepository
) : IUseCase<Category, Category> {

    override fun execute(request: Category): Category {
        if (request.name.isBlank())
            throw ValidationException("Category name should not be empty")
        if (request.description.isBlank())
            throw ValidationException("Category description should not be empty")

        return categoryRepository.save(request)
    }
}