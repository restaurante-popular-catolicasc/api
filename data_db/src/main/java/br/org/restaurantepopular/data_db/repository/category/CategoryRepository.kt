package br.org.restaurantepopular.data_db.repository.category

import br.org.restaurantepopular.data_db.mapper.CategoryEntityMapper
import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.core.exception.NotFoundException

class CategoryRepository(
    private val categoryDAO: CategoryDAO
) : ICategoryRepository {

    override fun save(category: Category): Category {
        return CategoryEntityMapper.toCategory(
            categoryDAO.save(CategoryEntityMapper.toCategoryEntity(category))
        )
    }

    @Throws(NotFoundException::class)
    override fun update(category: Category) {
        if (!categoryDAO.existsById(category.id!!))
            throw NotFoundException(String.format("Not found a category with this %s ID", category.id))

        categoryDAO.save(CategoryEntityMapper.toCategoryEntity(category))
    }

    override fun listCategories(): List<Category> {
        return categoryDAO
            .findAll()
            .map(CategoryEntityMapper::toCategory)
    }

    @Throws(NotFoundException::class)
    override fun findById(id: String): Category {
        val categoryOptional =
            categoryDAO.findById(id)
                .map(CategoryEntityMapper::toCategory)

        if (categoryOptional.isEmpty)
            throw NotFoundException(String.format("Not found a category with this %s ID", id))
        return categoryOptional.get()
    }

    override fun delete(id: String) {
        if (!categoryDAO.existsById(id))
            throw NotFoundException(String.format("Not found a category with this %s ID", id))

        categoryDAO.deleteById(id)
    }
}
