package br.org.restaurantepopular.usecase.category;

import br.org.restaurantepopular.entity.Category;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.List;
import java.util.Optional;

public class ListCategoriesUseCase implements IUseCase<Optional, List<Category>> {

    private final ICategoryRepository categoryRepository;

    public ListCategoriesUseCase(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> execute(Optional optional) {
        return categoryRepository.listCategories();
    }
}
