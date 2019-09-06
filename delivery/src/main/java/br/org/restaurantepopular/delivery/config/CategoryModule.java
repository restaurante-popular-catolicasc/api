package br.org.restaurantepopular.delivery.config;

import br.org.restaurantepopular.data_db.repository.category.CategoryDAO;
import br.org.restaurantepopular.data_db.repository.category.CategoryRepository;
import br.org.restaurantepopular.delivery.rest.impl.CategoryResource;
import br.org.restaurantepopular.usecase.category.*;
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryModule {

    @Bean
    public CategoryResource categoryResource(
            IUseCaseExecutor useCaseExecutor,
            GetCategoryByIdUseCase getCategoryByIdUseCase,
            CreateCategoryUseCase createCategoryUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            ListCategoriesUseCase listCategoriesUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase) {
        return new CategoryResource(
                useCaseExecutor,
                getCategoryByIdUseCase,
                createCategoryUseCase,
                updateCategoryUseCase,
                listCategoriesUseCase,
                deleteCategoryUseCase
        );
    }

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

