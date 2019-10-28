package br.org.restaurantepopular.data_db.mapper

import br.org.restaurantepopular.data_db.entity.CategoryEntity
import br.org.restaurantepopular.entity.Category

object CategoryEntityMapper {

    fun toCategoryEntity(category: Category): CategoryEntity {
        return CategoryEntity(
            id = category.id ?: "",
            name = category.name,
            description = category.description
        )
    }

    fun toCategory(categoryEntity: CategoryEntity): Category {
        return Category(
            id = categoryEntity.id,
            name = categoryEntity.name,
            description = categoryEntity.description
        )
    }
}