package br.org.restaurantepopular.usecase.product_type

import br.org.restaurantepopular.entity.ProductType

interface IProductTypeRepository {

    fun save(productType: ProductType): ProductType

    fun update(productType: ProductType)

    fun listProductTypes(): List<ProductType>

    fun findById(id: String): ProductType

    fun delete(id: String)
}
