package br.org.restaurantepopular.data_db.repository.product_type

import br.org.restaurantepopular.data_db.entity.ProductTypeEntity
import br.org.restaurantepopular.data_db.mapper.toProductType
import br.org.restaurantepopular.data_db.mapper.toProductTypeEntity
import br.org.restaurantepopular.entity.ProductType
import br.org.restaurantepopular.usecase.core.exception.NotFoundException
import br.org.restaurantepopular.usecase.product_type.IProductTypeRepository

class ProductTypeRepository(
    private val productTypeDAO: ProductTypeDAO
) : IProductTypeRepository {

    override fun save(productType: ProductType): ProductType {
        return productTypeDAO.save(productType.toProductTypeEntity()).toProductType()
    }

    @Throws(NotFoundException::class)
    override fun update(productType: ProductType) {
        if (!productTypeDAO.existsById(productType.id!!))
            throw NotFoundException(String.format("Not found a product type with this %s ID", productType.id))

        productTypeDAO.save(productType.toProductTypeEntity())
    }

    override fun listProductTypes(): List<ProductType> {
        return productTypeDAO
            .findAll()
            .map(ProductTypeEntity::toProductType)
    }

    override fun findById(id: String): ProductType {
        val productTypeOptional =
            productTypeDAO.findById(id)
                .map(ProductTypeEntity::toProductType)

        if (productTypeOptional.isEmpty)
            throw NotFoundException(String.format("Not found a category with this %s ID", id))

        return productTypeOptional.get()
    }

    override fun delete(id: String) {
        if (!productTypeDAO.existsById(id))
            throw NotFoundException(String.format("Not found a product type with this %s ID", id))

        productTypeDAO.deleteById(id)
    }
}
