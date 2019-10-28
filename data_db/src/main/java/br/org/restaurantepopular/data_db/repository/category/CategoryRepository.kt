package br.org.restaurantepopular.data_db.repository.category

import br.org.restaurantepopular.data_db.entity.CategoryEntity
import br.org.restaurantepopular.data_db.mapper.toCategory
import br.org.restaurantepopular.data_db.mapper.toCategoryEntity
import br.org.restaurantepopular.entity.Category
import br.org.restaurantepopular.usecase.category.ICategoryRepository
import br.org.restaurantepopular.usecase.core.exception.NotFoundException

class CategoryRepository(
    private val categoryDAO: CategoryDAO
) : ICategoryRepository {

    override fun save(category: Category): Category {
        return categoryDAO.save(category.toCategoryEntity()).toCategory()
    }

    @Throws(NotFoundException::class)
    override fun update(category: Category) {
        if (!categoryDAO.existsById(category.id!!))
            throw NotFoundException(String.format("Not found a category with this %s ID", category.id))

        categoryDAO.save(category.toCategoryEntity())
    }

    override fun listCategories(): List<Category> {
        return categoryDAO
            .findAll()
            .map(CategoryEntity::toCategory)
    }

    @Throws(NotFoundException::class)
    override fun findById(id: String): Category {
        val categoryOptional =
            categoryDAO.findById(id)
                .map(CategoryEntity::toCategory)

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
