package br.org.restaurantepopular.delivery.config

import br.org.restaurantepopular.data_db.repository.category.CategoryDAO
import br.org.restaurantepopular.data_db.repository.category.CategoryRepository
import br.org.restaurantepopular.usecase.category.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CategoryModule {

    @Bean
    fun getCategoryByIdUseCase(categoryRepository: ICategoryRepository) = GetCategoryByIdUseCase(categoryRepository)

    @Bean
    fun createCategoryUseCase(categoryRepository: ICategoryRepository) = CreateCategoryUseCase(categoryRepository)

    @Bean
    fun updateCategoryUseCase(categoryRepository: ICategoryRepository) = UpdateCategoryUseCase(categoryRepository)

    @Bean
    fun listCategoriesUseCase(categoryRepository: ICategoryRepository) = ListCategoriesUseCase(categoryRepository)

    @Bean
    fun deleteCategoryUseCase(categoryRepository: ICategoryRepository) = DeleteCategoryUseCase(categoryRepository)

    @Bean
    fun categoryRepository(categoryDAO: CategoryDAO) = CategoryRepository(categoryDAO)
}