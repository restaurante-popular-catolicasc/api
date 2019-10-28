package br.org.restaurantepopular.data_db.mapper

import br.org.restaurantepopular.data_db.entity.ProductTypeEntity
import br.org.restaurantepopular.entity.ProductType

fun ProductType.toProductTypeEntity(): ProductTypeEntity {
    return ProductTypeEntity(
        id = this.id,
        name = this.name
    )
}

fun ProductTypeEntity.toProductType(): ProductType {
    return ProductType(
        id = this.id,
        name = this.name
    )

}