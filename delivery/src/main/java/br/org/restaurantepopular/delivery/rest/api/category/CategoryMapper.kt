package br.org.restaurantepopular.delivery.rest.api.category

import br.org.restaurantepopular.entity.Category

fun CategoryDTO.toCategory(): Category {
    return Category(
        id = this.id ?: "",
        name = this.name ?: "",
        description = this.description ?: ""
    )
}

fun Category.toCategoryDto(): CategoryDTO {
    return CategoryDTO(
        id = this.id,
        name = this.name,
        description = this.description
    )
}