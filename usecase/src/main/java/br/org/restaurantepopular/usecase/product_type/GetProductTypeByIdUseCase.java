package br.org.restaurantepopular.usecase.product_type;

import br.org.restaurantepopular.entity.ProductType;
import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class GetProductTypeByIdUseCase implements IUseCase<String, Optional<ProductType>> {

    private final IProductTypeRepository categoryRepository;

    public GetProductTypeByIdUseCase(IProductTypeRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<ProductType> execute(String id) {
        return categoryRepository.findById(id);
    }
}
