package br.org.restaurantepopular.usecase.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.core.IUseCase;
import br.org.restaurantepopular.usecase.core.exception.ValidationException;

import java.util.Optional;

public class CreateCategoryUseCase implements IUseCase<Category, Optional> {

    private final ICategoryRepository categoryRepository;

    public CreateCategoryUseCase(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional execute(Category category) {
        if (category.getName() == null || category.getName().isBlank() || category.getName().isEmpty())
            throw new ValidationException("Category name should not be empty");
        if (category.getDescription() == null || category.getDescription().isBlank() || category.getDescription().isEmpty())
            throw new ValidationException("Category description should not be empty");

        categoryRepository.save(category);
        return Optional.empty();
    }
}
