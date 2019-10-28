package br.org.restaurantepopular.delivery.rest.api.product_type

import br.org.restaurantepopular.entity.ProductType

fun ProductType.toProductTypeDTO(): ProductTypeDTO {
    return ProductTypeDTO(
        id = this.id,
        name = this.name
    )
}

fun ProductTypeDTO.toProductType(): ProductType {
    return ProductType(
        id = this.id,
        name = this.name ?: ""
    )
}