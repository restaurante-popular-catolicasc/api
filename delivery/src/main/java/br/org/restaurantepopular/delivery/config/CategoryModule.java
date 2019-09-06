package br.org.restaurantepopular.delivery.config;

import br.org.restaurantepopular.data_db.repository.category.CategoryDAO;
import br.org.restaurantepopular.data_db.repository.category.CategoryRepository;
import br.org.restaurantepopular.usecase.category.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryModule {

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase(
            ICategoryRepository categoryRepository) {
        return new GetCategoryByIdUseCase(categoryRepository);
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(
            ICategoryRepository categoryRepository) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(
            ICategoryRepository categoryRepository) {
        return new UpdateCategoryUseCase(categoryRepository);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase(
            ICategoryRepository categoryRepository) {
        return new ListCategoriesUseCase(categoryRepository);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(
            ICategoryRepository categoryRepository) {
        return new DeleteCategoryUseCase(categoryRepository);
    }

    @Bean
    public ICategoryRepository categoryRepository(CategoryDAO categoryDAO) {
        return new CategoryRepository(categoryDAO);
    }
}

