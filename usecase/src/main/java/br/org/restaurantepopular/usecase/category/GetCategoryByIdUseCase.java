package br.org.restaurantepopular.usecase.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class GetCategoryByIdUseCase implements IUseCase<String, Optional<Category>> {

    private final ICategoryRepository categoryRepository;

    public GetCategoryByIdUseCase(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> execute(String id) {
        return categoryRepository.findById(id);
    }
}
