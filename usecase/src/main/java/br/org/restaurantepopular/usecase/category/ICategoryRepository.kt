package br.org.restaurantepopular.usecase.category

import br.org.restaurantepopular.entity.Category

interface ICategoryRepository {
    fun save(category: Category): Category
    fun update(category: Category)
    fun listCategories(): List<Category>
    fun findById(id: String): Category
    fun delete(id: String)
}