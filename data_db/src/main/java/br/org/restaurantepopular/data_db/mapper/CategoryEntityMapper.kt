package br.org.restaurantepopular.data_db.mapper

import br.org.restaurantepopular.data_db.entity.CategoryEntity
import br.org.restaurantepopular.entity.Category


fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = this.id ?: "",
        name = this.name,
        description = this.description
    )
}

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name,
        description = this.description
    )
}
