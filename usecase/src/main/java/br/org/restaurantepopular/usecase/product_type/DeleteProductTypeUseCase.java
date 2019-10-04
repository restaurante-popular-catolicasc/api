package br.org.restaurantepopular.usecase.product_type;

import br.org.restaurantepopular.usecase.core.IUseCase;

import java.util.Optional;

public class DeleteProductTypeUseCase implements IUseCase<String, Optional> {

    private final IProductTypeRepository productTypeRepository;

    public DeleteProductTypeUseCase(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public Optional execute(String s) {
        productTypeRepository.delete(s);
        return Optional.empty();
    }
}
