package br.org.restaurantepopular.delivery.rest.impl;

import br.org.restaurantepopular.delivery.rest.api.category.CategoryDTO;
import br.org.restaurantepopular.delivery.rest.api.category.CategoryMapper;
import br.org.restaurantepopular.delivery.rest.api.category.ICategoryResource;
import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.category.*;
import br.org.restaurantepopular.usecase.core.IUseCaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
public class CategoryResource implements ICategoryResource {

    private final IUseCaseExecutor useCaseExecutor;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Autowired
    public CategoryResource(IUseCaseExecutor useCaseExecutor, GetCategoryByIdUseCase getCategoryByIdUseCase, CreateCategoryUseCase createCategoryUseCase, UpdateCategoryUseCase updateCategoryUseCase, ListCategoriesUseCase listCategoriesUseCase, DeleteCategoryUseCase deleteCategoryUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @Override
    public CompletionStage<CategoryDTO> getCategoryById(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                getCategoryByIdUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional<Category> category) ->
                        category.map(CategoryMapper::toCategoryDTO).get()
        );
    }

    @Override
    public CompletionStage<ResponseEntity<CategoryDTO>> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return useCaseExecutor.invoke(
                createCategoryUseCase,
                categoryDTO,
                CategoryMapper::toCategory,
                (Optional<Category> category) ->
                        new ResponseEntity<>(
                                category.map(CategoryMapper::toCategoryDTO).get(),
                                HttpStatus.CREATED
                        )
        );
    }

    @Override
    public CompletionStage<ResponseEntity> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return useCaseExecutor.invoke(
                updateCategoryUseCase,
                categoryDTO,
                CategoryMapper::toCategory,
                (Optional v) -> new ResponseEntity(HttpStatus.OK)
        );
    }

    @Override
    public CompletionStage<List<CategoryDTO>> listCategories() {
        return useCaseExecutor.invoke(
                listCategoriesUseCase,
                (List<Category> categories) ->
                        categories
                                .stream()
                                .map(CategoryMapper::toCategoryDTO)
                                .collect(Collectors.toList())
        );
    }

    @Override
    public CompletionStage<ResponseEntity> deleteCategory(@PathVariable("id") String id) {
        return useCaseExecutor.invoke(
                deleteCategoryUseCase,
                id,
                (String requestDTO) -> requestDTO,
                (Optional v) -> new ResponseEntity(HttpStatus.NO_CONTENT)
        );
    }
}
