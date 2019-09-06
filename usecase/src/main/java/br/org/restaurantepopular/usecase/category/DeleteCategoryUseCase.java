package br.org.restaurantepopular.usecase.category;

import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class DeleteCategoryUseCase implements IUseCase<String, Optional> {

    private final ICategoryRepository categoryRepository;

    public DeleteCategoryUseCase(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional execute(String s) {
        categoryRepository.delete(s);
        return Optional.empty();
    }
}
